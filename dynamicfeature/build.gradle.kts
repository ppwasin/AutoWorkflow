plugins {
	id("com.android.dynamic-feature")
	id("org.jetbrains.kotlin.android")
	id("plugin.junit")
}
android {
	namespace = "com.boot.dynamicfeature"
	val versions = infra.versions
	compileSdk = versions.compileSdk.get().toInt()
	defaultConfig {
		minSdk = versions.minSdk.get().toInt()
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	compileOptions {
		sourceCompatibility = JavaVersion.toVersion(versions.java.get())
		targetCompatibility = JavaVersion.toVersion(versions.java.get())
	}
	kotlinOptions {
		jvmTarget = versions.java.get()
	}
}

dependencies {
	implementation(projects.app)
	addComposeDependencies(project)

	implementation(libs.appcompat)
	implementation(projects.platform.designSystem)
	implementation(projects.platform.components)

	implementation(libs.bundles.coroutine)
	implementation(libs.paging.compose)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}
