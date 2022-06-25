@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.convention.composeandroidlib")
    id("plugin.junit")
    id("plugin.spotless")
    alias(libs.plugins.paparazzi)
}

configure<JunitPluginConfig> {
    includeJunit4.set(true)
}

dependencies {
    implementation(projects.platform.designSystem)
    implementation(libs.appcompat)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.coroutine)
    
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)

    androidTestImplementation(libs.androidTest.espresso)
    androidTestImplementation(libs.androidTest.junit)
    androidTestImplementation(libs.androidTest.compose)
    debugImplementation(libs.test.composeRule)
    testImplementation(libs.test.composeRule)
}