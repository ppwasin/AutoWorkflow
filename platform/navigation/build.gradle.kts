import com.convention.addComposeDependencies

plugins {
	id("convention.android.lib")
	id("convention.android.compose")
	id("plugin.junit")
}
android {
	namespace = "com.boot.navigation"
}
dependencies {
	addComposeDependencies(project)
	implementation(libs.bundles.coroutine)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}
