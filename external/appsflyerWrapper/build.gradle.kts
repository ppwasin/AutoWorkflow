import com.convention.addComposeDependencies

plugins {
	id("convention.android.lib")
	id("convention.android.compose")
	id("plugin.junit")
}

android {
	namespace = "com.boot.external.appsflyerWrapper"
}

dependencies {
	addComposeDependencies(project)
	implementation(libs.bundles.coroutine)
	implementation(projects.platform.designSystem)
	api(libs.appsflyer)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}
