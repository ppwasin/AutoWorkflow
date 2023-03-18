plugins {
	id("com.convention.android")
	id("com.convention.android-compose")
	id("plugin.junit")
	id("kotlin-parcelize")
}
android {
	namespace = "com.boot.entrypoint"
}
dependencies {
	implementation(projects.platform.designSystem)
	implementation(projects.platform.components)
	implementation(projects.features.payment)
	implementation(projects.fake.pagination)
//    implementation(projects.shopping)
	implementation(libs.bundles.compose)
	implementation(libs.bundles.coroutine)
	implementation(libs.paging.compose)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
	androidTestImplementation(libs.androidTest.compose)
	debugImplementation(libs.test.composeRule)
}
