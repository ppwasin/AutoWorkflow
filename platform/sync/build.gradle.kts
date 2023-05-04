import com.convention.addComposeDependencies

plugins {
	id("com.convention.android.lib")
	id("com.convention.android.compose")
	id("plugin.junit")
}
android {
	namespace = "com.boot.sync"
}

dependencies {
	addComposeDependencies(project)
	implementation(libs.bundles.coroutine)
	implementation(libs.startup)
	implementation(libs.work.runtimektx)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.work.androidtest)
	androidTestImplementation(libs.androidTest.junit)
}
