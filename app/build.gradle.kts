import com.convention.addComposeDependencies
import com.convention.setupAndroidApplication
import com.github.triplet.gradle.androidpublisher.ResolutionStrategy.AUTO
import java.util.*

plugins {
	id("convention.android.app")
	id("convention.android.compose")
	id("plugin.junit")
	id("kotlin-parcelize")
	id("plugin.showkase")
	id(infra.plugins.googleServices.get().pluginId)
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
	val projectBuildConfig = com.convention.configs.ProjectBuild.create(infra)
	setupAndroidApplication(
		config = projectBuildConfig,
		versionName = versionNameOverride,
		applicationId = "com.boot.autoworkflow",
	)

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
	}

	val dynamicFeatureModules = setOf(
		projects.dynamicFeatureSample.onDemend.dependencyProject.path,
		projects.dynamicFeatureSample.onInstall.dependencyProject.path,
	)
	println("Application build with dynamic-features: $dynamicFeatureModules")
	dynamicFeatures += dynamicFeatureModules
}

dependencies {
	implementation(projects.features.entrypoint)
	implementation(projects.features.payment)
	implementation(projects.features.recipe)
	addComposeDependencies(project)

	implementation(libs.appcompat)
	implementation(libs.material)
	implementation(libs.splashscreen)
	implementation(libs.bundles.compose)
	implementation(libs.bundles.coroutine)

	implementation(projects.dynamicFeatureSample.consumer)
	implementation(libs.playCore)
	implementation(libs.playCoreKtx)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}
