import com.convention.addComposeDependencies

plugins {
	id("com.convention.android.lib")
	id("com.convention.android.compose")
	id("plugin.junit")
}

android {
	namespace = "com.boot.fake"
}

dependencies {
	implementation(projects.platform.designSystem)
	addComposeDependencies(project)
	implementation(libs.bundles.coroutine)

	implementation(libs.paging.compose)
	implementation(libs.paging.runtime)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}
