import com.convention.extensions.setupCompose
import com.convention.extensions.setupSdk

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("plugin.junit5")
    id("plugin.spotless")
    id("plugin.dagger")
}

android {
    setupCompose(libs.versions.compose.get())
    setupSdk(versionName = "1.0", applicationId = "com.boot.playground")
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(libs.splashscreen)
    implementation(projects.platform.designSystem)

    implementation(libs.bundles.compose)
    implementation(libs.bundles.coroutine)

    implementation(libs.rx.android)
    implementation(libs.rx.java)

    testImplementation(libs.test.coroutine)
    androidTestImplementation(libs.androidTest.espresso)
    androidTestImplementation(libs.androidTest.junit)
}