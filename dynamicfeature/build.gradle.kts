import com.convention.extensions.kotlinOptions
import com.convention.configs.ProjectBuild

plugins {
	id("com.android.dynamic-feature")
	id("org.jetbrains.kotlin.android")
	id("plugin.junit")
}
android {
	namespace = "com.boot.dynamicfeature"
	compileSdk = ProjectBuild.compileSdk
	defaultConfig {
		minSdk = ProjectBuild.minSdk
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	compileOptions {
		sourceCompatibility = ProjectBuild.java
		targetCompatibility = ProjectBuild.java
	}
	kotlinOptions {
		jvmTarget = ProjectBuild.java.toString()
	}
}

dependencies {
	implementation(projects.app)
	implementation(libs.appcompat)
	implementation(projects.platform.designSystem)
	implementation(projects.platform.components)
	implementation(libs.bundles.compose)
	implementation(libs.bundles.coroutine)
	implementation(libs.paging.compose)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
	androidTestImplementation(libs.androidTest.compose)
	debugImplementation(libs.test.composeRule)
}
