import com.github.triplet.gradle.androidpublisher.ResolutionStrategy.AUTO
import com.convention.extensions.setupCompose
import com.convention.extensions.setupSdk
import java.util.*

apply<Junit5Plugin>()
apply<SpotlessPlugin>()

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application")
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
    setupCompose(libs.versions.compose.get())
    setupSdk(versionName = versionNameOverride, applicationId = "com.boot.autoworkflow")

    val keystorePropertiesFile = rootProject.file("keystore.properties")
    val signConfigName = "config"
    val hasKeyStore = keystorePropertiesFile.exists()
    if (hasKeyStore) {
        val keystoreProperties = Properties()
        keystoreProperties.load(keystorePropertiesFile.inputStream())
        signingConfigs {
            create(signConfigName) {
                storeFile = rootProject.file(keystoreProperties.getProperty("storeFile"))
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
                "proguard-rules.pro"
            )
            firebaseAppDistribution {
                serviceCredentialsFile = "project-firebase-api-service.json"
                artifactType = "AAB"
                testers = "pp.wasin@gmail.com, ex@gmail.com"
            }
        }
    }
}

dependencies {
    implementation(projects.features.entrypoint)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.splashscreen)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.coroutine)

    androidTestImplementation(libs.androidTest.espresso)
    androidTestImplementation(libs.androidTest.junit)
}
