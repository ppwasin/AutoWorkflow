plugins {
	id("com.convention.android")
	id("com.convention.android-compose")
	id("plugin.junit")
	id("plugin.spotless")
}
android {
	namespace = "com.boot.payment"
}
dependencies {
	implementation(projects.platform.designSystem)
	implementation(projects.platform.components)
	implementation(libs.billing)
	implementation(libs.bundles.compose)
	implementation(libs.bundles.coroutine)
	implementation(libs.paging.compose)
	implementation(libs.bundles.arrowkt)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
	androidTestImplementation(libs.androidTest.compose)
	debugImplementation(libs.test.composeRule)
}
