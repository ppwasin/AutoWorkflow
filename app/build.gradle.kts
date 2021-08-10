import com.github.triplet.gradle.androidpublisher.ResolutionStrategy.AUTO
import java.util.*

apply<plugin.Junit5Plugin>()

apply<plugin.spotless.SpotlessPlugin>()

plugins {
  id("com.android.application")
  id("kotlin-android")
  id(Build.GoogleService.pluginId)
  id(Build.FirebaseAppDistribution.pluginId)
  id(Build.PlayPublisher.pluginId)
}

/** Publish **/
val versionNameOverride =
  if(project.hasProperty("versionName")) project.property("versionName").toString()
  else "No versionName"
play {
  track.set("internal") //default is `internal`
  serviceAccountCredentials.set(rootProject.file("google-api-service.json"))
  defaultToAppBundles.set(true)
  artifactDir.set(file("build/outputs/bundle/release"))
  resolutionStrategy.set(AUTO) //Automatically increase versionCode
}
//androidComponents {
//  onVariants {
//    println("versionName: $versionNameOverride")
//    for (output in it.outputs) {
//      output.versionName.set(versionNameOverride)
//    }
//  }
//}

android {
  compileSdk = Build.compileSdk

  defaultConfig {
    applicationId = Build.appId
    minSdk = Build.minSdk
    targetSdk = Build.targetSdk
    versionCode = 1
    versionName = versionNameOverride

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  val keystorePropertiesFile = rootProject.file("keystore.properties")
  val signConfigName = "config"
  if (keystorePropertiesFile.exists()) {
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
      signingConfig = signingConfigs.getByName(signConfigName)
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      firebaseAppDistribution {
        artifactType = "AAB"
        testers = "pp.wasin@gmail.com, ex@gmail.com"
      }
    }
  }
  compileOptions {
    sourceCompatibility = Build.java
    targetCompatibility = Build.java
  }
  kotlinOptions { jvmTarget = Build.java.toString() }
  buildFeatures {
    compose = true

    // Disable unused AGP features
    buildConfig = true
    aidl = false
    renderScript = false
    resValues = false
    shaders = false
  }
  composeOptions { kotlinCompilerExtensionVersion = Versions.compose }
  packagingOptions {
    // Multiple dependency bring these files in. Exclude them to enable
    // our test APK to build (has no effect on our AARs)
    resources.excludes.run {
      add("META-INF/AL2.0")
      add("META-INF/LGPL2.1")
    }
  }
}

dependencies {
  implementation(project(":features:home"))
  implementation(platform(Build.GoogleService.firebasePlatform))

  implementation(Deps.appcompat)
  implementation(Deps.material)
  implementation(Deps.Compose.activity)
  implementation(Deps.Compose.ui)
  implementation(Deps.Compose.uiTooling)
  implementation(Deps.Compose.foundation)
  implementation(Deps.Compose.material)
  implementation(Deps.Compose.materialIcon)
  implementation(Deps.Compose.materialIconsExt)
  implementation(Deps.Coroutine.core)
  implementation(Deps.Coroutine.android)

  androidTestImplementation(Deps.Test.espresso)
  androidTestImplementation(Deps.Test.junitInstrumental)
}
