import com.modular.plugin.extensions.setupCompose
import com.modular.plugin.extensions.setupSdk

apply<plugin.Junit5Plugin>()
apply<plugin.DaggerPlugin>()
apply<plugin.spotless.SpotlessPlugin>()
plugins {
    id("com.android.application")
    id("kotlin-android")
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

    implementation(libs.material)
    implementation(libs.appcompat)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")

    implementation(libs.bundles.compose)
    implementation(libs.bundles.coroutine)

    implementation(libs.rx.android)
    implementation(libs.rx.java)

    testImplementation(libs.test.coroutine)
    androidTestImplementation(libs.androidTest.espresso)
    androidTestImplementation(libs.androidTest.junit)
}