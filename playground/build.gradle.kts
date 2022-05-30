apply<plugin.Junit5Plugin>()
apply<plugin.DaggerPlugin>()
apply<plugin.spotless.SpotlessPlugin>()
plugins {
    id("com.modular.composelib")
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