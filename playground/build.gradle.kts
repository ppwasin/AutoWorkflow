apply<plugin.Junit5Plugin>()
apply<plugin.DaggerPlugin>()
apply<plugin.spotless.SpotlessPlugin>()
plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = Build.compileSdk

    defaultConfig {
        applicationId = "com.boot.playground"
        minSdk = Build.minSdk
        targetSdk = Build.targetSdk
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
        sourceCompatibility = Build.java
        targetCompatibility = Build.java
    }
    kotlinOptions {
        jvmTarget = Build.java.toString()
//        languageVersion = "1.5"
//        apiVersion = "1.5"
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

    implementation(Deps.material)
    implementation(Deps.appcompat)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    androidTestImplementation(Deps.Test.espresso)
    androidTestImplementation(Deps.Test.junitInstrumental)

    implementation(Deps.Compose.activity)
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.uiTooling)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.materialIcon)
    implementation(Deps.Compose.materialIconsExt)
    //    androidTestImplementation(Deps.Compose.uiTesting)

    implementation(Deps.Rx.rxJava)
    implementation(Deps.Rx.rxAndroid)

    implementation(Deps.Coroutine.core)
    implementation(Deps.Coroutine.android)
    testImplementation(Deps.Coroutine.test)
}