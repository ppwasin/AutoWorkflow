plugins {
	id("com.convention.android.lib")
	id("com.convention.android.compose")
	alias(infra.plugins.ksp)
	id("plugin.junit")
	id("plugin.room")
}
android {
	namespace = "com.boot.recipe"
}
dependencies {
	implementation(libs.paging.compose)
	implementation(libs.paging.runtime)
	implementation(libs.paging.room)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}
