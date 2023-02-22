plugins {
	id("com.convention.android")
	id("com.convention.android-compose")
	id("plugin.junit")
	id("plugin.spotless")
}
android{
	namespace = "com.boot.designSystem"
}
dependencies {
	implementation(libs.bundles.compose)
	implementation(libs.bundles.coroutine)
	implementation(libs.material)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
	androidTestImplementation(libs.androidTest.compose)
	debugImplementation(libs.test.composeRule)
}
