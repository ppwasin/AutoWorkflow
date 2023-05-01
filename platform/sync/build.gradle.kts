plugins {
	id("com.convention.android")
	id("com.convention.android-compose")
	id("plugin.junit")
}
android {
	namespace = "com.boot.sync"
}

dependencies {
	implementation(platform(libs.compose.bom))
	implementation(libs.bundles.compose)
	implementation(libs.bundles.coroutine)
	implementation(libs.startup)
	implementation(libs.work.runtimektx)

	androidTestImplementation(platform(libs.compose.bom))
	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.work.androidtest)
	androidTestImplementation(libs.androidTest.junit)
	androidTestImplementation(libs.androidTest.compose)
	debugImplementation(libs.test.composeRule)
}
