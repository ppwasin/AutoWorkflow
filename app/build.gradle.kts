import com.github.triplet.gradle.androidpublisher.ResolutionStrategy.AUTO
import java.util.*

//apply<Junit5Plugin>()
//apply<SpotlessPlugin>()

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	id("com.android.application")
	id("com.convention.android-compose")
	id("plugin.junit")
	id("plugin.spotless")
	id("kotlin-parcelize")
//    id("kotlin-android")
	kotlin("android")
	id(infra.plugins.googleServices.get().pluginId)
//    alias(infra.plugins.googleServices)
	id(infra.plugins.firebaseAppdistribution.get().pluginId)
	id(infra.plugins.playPublisher.get().pluginId)
}

/** Publish **/
val versionNameOverride =
	providers.gradleProperty("versionName").orNull ?: "No versionName"
play {
	track.set("alpha") //default is `internal`
	serviceAccountCredentials.set(rootProject.file("google-api-service.json"))
	defaultToAppBundles.set(true)
	artifactDir.set(file("build/outputs/bundle/release"))
	resolutionStrategy.set(AUTO) //Automatically increase versionCode
}

android {
	namespace = "com.boot.autoworkflow"
	setupSdk(versionNameOverride, "com.boot.autoworkflow")

	val keystorePropertiesFile = rootProject.file("keystore.properties")
	val signConfigName = "config"
	val hasKeyStore = keystorePropertiesFile.exists()
	if (hasKeyStore) {
		val keystoreProperties = Properties()
		keystoreProperties.load(keystorePropertiesFile.inputStream())
		signingConfigs {
			create(signConfigName) {
				storeFile =
					rootProject.file(keystoreProperties.getProperty("storeFile"))
				storePassword = keystoreProperties.getProperty("storePassword")
				keyAlias = keystoreProperties.getProperty("keyAlias")
				keyPassword = keystoreProperties.getProperty("keyPassword")
			}
		}
	}

	buildTypes {
		getByName("release") {
			if (hasKeyStore) signingConfig = signingConfigs.getByName(signConfigName)

			isMinifyEnabled = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro",
			)
			firebaseAppDistribution {
				serviceCredentialsFile = "project-firebase-api-service.json"
				artifactType = "AAB"
				testers = "pp.wasin@gmail.com, ex@gmail.com"
			}
		}
//		dynamicFeatures += setOf(":dynamicfeature")
	}
}

dependencies {
	implementation(projects.features.entrypoint)

	implementation(libs.appcompat)
	implementation(libs.material)
	implementation(libs.splashscreen)
	implementation(libs.bundles.compose)
	implementation(libs.bundles.coroutine)
	implementation(libs.zipline)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}

fun com.android.build.gradle.internal.dsl.BaseAppModuleExtension.setupSdk(
	versionName: String,
	applicationId: String
) {
	compileSdk = 33
	defaultConfig {
		this.applicationId = applicationId
		this.minSdk = 23
		this.targetSdk = 33
		this.versionCode = 1
		this.versionName = versionName
		this.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_11.toString()
	}
}
