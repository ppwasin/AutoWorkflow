@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	id("com.convention.android")
	id("com.convention.android-compose")
	alias(libs.plugins.paparazzi)
}

android{
	namespace = "com.boot.components.snapshot"
}

dependencies {
	implementation(projects.platform.designSystem)
	implementation(projects.platform.components)
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
