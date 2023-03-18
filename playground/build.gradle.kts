plugins {
	id("com.android.application")
	id("com.convention.android-compose")
	id("kotlin-android")
	id("plugin.junit")
	id("plugin.dagger")
}

android {
	namespace = "com.boot.playground"
	setupSdk(versionName = "1.0", applicationId = "com.boot.playground")
	buildTypes {
		getByName("release") {
			isMinifyEnabled = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro",
			)
		}
	}
}

dependencies {
	implementation(libs.splashscreen)
	implementation(projects.platform.designSystem)
	implementation(projects.external.appsflyerWrapper)

	implementation(libs.bundles.compose)
	implementation(libs.bundles.coroutine)

	implementation(libs.rx.android)
	implementation(libs.rx.java)

	testImplementation(libs.test.coroutine)
	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
	implementation(libs.appcompanist.permission)
	implementation(libs.datastoreCore)
	implementation(libs.datastoreAndroid)
	implementation(libs.activityKtx)
	implementation(libs.activity)
	implementation(libs.lifecycle.process)

	//Playground libraries
	implementation("com.google.android.gms:play-services-ads:21.1.0")
//  implementation("com.google.android.gms:play-services-ads-identifier:18.0.1")

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
	androidTestImplementation(libs.androidTest.compose)
	debugImplementation(libs.test.composeRule)
}

fun com.android.build.gradle.internal.dsl.BaseAppModuleExtension.setupSdk(
	versionName: String,
	applicationId: String
) {
	compileSdk = 33
	defaultConfig {
		this.applicationId = applicationId
		this.minSdk = 23
		this.targetSdk = 33
		this.versionCode = 1
		this.versionName = versionName
		this.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_11.toString()
	}
}
