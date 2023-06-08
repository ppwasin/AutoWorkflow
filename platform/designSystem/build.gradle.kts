import com.convention.addComposeDependencies

plugins {
	id("convention.android.lib")
	id("convention.android.compose")
	id("plugin.junit")
	id("plugin.showkase")
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
