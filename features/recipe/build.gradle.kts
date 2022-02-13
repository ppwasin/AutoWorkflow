apply<plugin.Junit5Plugin>()
apply<plugin.spotless.SpotlessPlugin>()
plugins {
    id("com.android.library")
    id("kotlin-android")
    alias(libs.plugins.ksp)
}

android {
    compileSdk = Build.compileSdk

    defaultConfig {
        minSdk = Build.minSdk
        targetSdk = Build.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = Build.java
        targetCompatibility = Build.java
    }
    kotlinOptions {
        jvmTarget = Build.java.toString()
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
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
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

    compileOnly(libs.ksp.processing)
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)
    implementation(libs.paging.room)

    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    androidTestImplementation(libs.androidTest.espresso)
    androidTestImplementation(libs.androidTest.junit)
}
