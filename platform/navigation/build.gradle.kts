plugins {
	id("com.convention.android")
	id("com.convention.android-compose")
	id("plugin.junit")
}
android {
	namespace = "com.boot.navigation"
}
dependencies {
	implementation(platform(libs.compose.bom))
	implementation(libs.bundles.compose)
	implementation(libs.bundles.coroutine)

	androidTestImplementation(platform(libs.compose.bom))
	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
	androidTestImplementation(libs.androidTest.compose)
	debugImplementation(libs.test.composeRule)
}
