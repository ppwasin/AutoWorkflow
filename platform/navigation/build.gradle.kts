apply<plugin.Junit5Plugin>()
apply<plugin.spotless.SpotlessPlugin>()
plugins {
    id("com.modular.composelib")
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.bundles.coroutine)

    androidTestImplementation(libs.androidTest.espresso)
    androidTestImplementation(libs.androidTest.junit)
    androidTestImplementation(libs.androidTest.compose)
    debugImplementation(libs.test.composeRule)
}