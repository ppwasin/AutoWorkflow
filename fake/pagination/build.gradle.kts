plugins {
	id("com.convention.android")
	id("com.convention.android-compose")
	id("plugin.junit")
}

android {
	namespace = "com.boot.fake"
}

dependencies {
	implementation(projects.platform.designSystem)
	implementation(platform(libs.compose.bom))
	implementation(libs.bundles.compose)
	implementation(libs.bundles.coroutine)

	implementation(libs.paging.compose)
	implementation(libs.paging.runtime)

	androidTestImplementation(platform(libs.compose.bom))
	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
	androidTestImplementation(libs.androidTest.compose)
	debugImplementation(libs.test.composeRule)
}
