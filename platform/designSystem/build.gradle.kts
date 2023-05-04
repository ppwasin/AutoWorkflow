import com.convention.addComposeDependencies

plugins {
	id("com.convention.android.lib")
	id("com.convention.android.compose")
	id("plugin.junit")
}
android {
	namespace = "com.boot.designSystem"
}
dependencies {
	addComposeDependencies(project)
	implementation(libs.bundles.coroutine)
	implementation(libs.material)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}
