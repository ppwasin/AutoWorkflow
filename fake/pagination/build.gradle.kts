apply<plugin.Junit5Plugin>()
apply<plugin.spotless.SpotlessPlugin>()
plugins {
    id("com.android.library")
    id("kotlin-android")
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
    implementation(project(":platform:designSystem"))
    implementation(Deps.Compose.activity)
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.uiTooling)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.material)
    implementation(Deps.Compose.materialIcon)
    implementation(Deps.Compose.materialIconsExt)
    implementation(Deps.Compose.navigation)
    implementation(Deps.Compose.swipeToRefresh)
    implementation(Deps.Compose.constraintLayout)
    implementation(Deps.Coroutine.core)
    implementation(Deps.Coroutine.android)
    
    implementation(Deps.Compose.paging)
    implementation(Deps.pagingRuntime)
    
    androidTestImplementation(Deps.Test.espresso)
    androidTestImplementation(Deps.Test.junitInstrumental)
    androidTestImplementation(Deps.Compose.uiTest)
    debugImplementation(Deps.Compose.uiTestManifest)
}