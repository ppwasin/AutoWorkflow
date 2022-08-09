plugins {
	id("com.android.dynamic-feature")
	id("org.jetbrains.kotlin.android")
  id("plugin.junit")
}
android {
	namespace = "com.boot.dynamicfeature"
	compileSdk = 32

	defaultConfig {
		minSdk = 24
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
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