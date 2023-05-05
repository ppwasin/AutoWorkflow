import com.convention.addComposeDependencies

plugins {
	id("com.convention.android.lib")
	id("com.convention.android.compose")
	alias(libs.plugins.paparazzi)
}

android {
	namespace = "com.boot.components.snapshot"
}

dependencies {
	implementation(projects.platform.designSystem)
	implementation(projects.platform.components)
	addComposeDependencies(project)
	implementation(libs.appcompat)
	implementation(libs.bundles.compose)
	implementation(libs.bundles.coroutine)

	implementation(libs.paging.compose)
	implementation(libs.paging.runtime)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}
