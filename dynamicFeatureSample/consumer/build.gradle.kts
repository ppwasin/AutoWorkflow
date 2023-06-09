import com.convention.addComposeDependencies

plugins {
	id("convention.android.lib")
	id("convention.android.compose")
	id("plugin.junit")
}

android {
	namespace = "com.boot.dynamicfeature.sample.consumer"
}

dependencies {
	implementation(projects.platform.dynamicfeature)
	implementation(projects.platform.designSystem)
	addComposeDependencies(project)
	implementation(libs.appcompat)
	implementation(libs.bundles.coroutine)
}
