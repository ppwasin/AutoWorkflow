apply<Junit5Plugin>()
apply<SpotlessPlugin>()
plugins {
    id("com.convention.composeandroidlib")
}

dependencies {
    implementation(libs.bundles.compose)
    implementation(libs.bundles.coroutine)

    androidTestImplementation(libs.androidTest.espresso)
    androidTestImplementation(libs.androidTest.junit)
    androidTestImplementation(libs.androidTest.compose)
    debugImplementation(libs.test.composeRule)
}