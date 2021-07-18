apply<plugin.Junit5Plugin>()
apply<plugin.DaggerPlugin>()
plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.boot.playground"
        minSdk = 23
        targetSdk = 30
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

    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

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