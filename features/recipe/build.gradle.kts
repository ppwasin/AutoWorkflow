apply<plugin.Junit5Plugin>()
apply<plugin.spotless.SpotlessPlugin>()
plugins {
    id("com.android.library")
    id("kotlin-android")
    id(Deps.KSP.plugin) version Versions.ksp
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

    compileOnly(Deps.KSP.processing)
    implementation(Deps.Room.runtime)
    ksp(Deps.Room.compiler)
    implementation(Deps.Room.ktxAndcoroutine)
    
    implementation(Deps.Compose.paging)
    implementation(Deps.pagingRuntime)
}
