plugins {
    id("com.convention.composeandroidlib")
    id("plugin.junit5")
    id("plugin.spotless")
}
dependencies {
    implementation(projects.platform.designSystem)
    implementation(projects.platform.components)
    implementation(projects.fake.pagination)
    implementation(projects.shopping)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.coroutine)
    implementation(libs.paging.compose)

    androidTestImplementation(libs.androidTest.espresso)
    androidTestImplementation(libs.androidTest.junit)
    androidTestImplementation(libs.androidTest.compose)
    debugImplementation(libs.test.composeRule)
}