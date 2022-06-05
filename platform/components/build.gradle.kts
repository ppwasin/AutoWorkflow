apply<plugin.Junit5Plugin>()
apply<plugin.spotless.SpotlessPlugin>()
plugins {
    id("com.convention.composeandroidlib")
}

dependencies {
    implementation(projects.platform.designSystem)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.coroutine)
    
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)

    androidTestImplementation(libs.androidTest.espresso)
    androidTestImplementation(libs.androidTest.junit)
    androidTestImplementation(libs.androidTest.compose)
    debugImplementation(libs.test.composeRule)
}