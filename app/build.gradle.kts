apply<plugin.Junit5Plugin>()
apply<plugin.spotless.SpotlessPlugin>()
plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = Deps.Build.compileSdk

    defaultConfig {
        applicationId = "com.boot.autoworkflow"
        minSdk = Deps.Build.minSdk
        targetSdk = Deps.Build.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Deps.Build.java
        targetCompatibility = Deps.Build.java
    }
    kotlinOptions {
        jvmTarget = Deps.Build.java.toString()
    }
    buildFeatures {
        compose = true

        // Disable unused AGP features
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
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