import com.convention.addComposeDependencies
import com.convention.setupAndroidApplication

plugins {
	id("convention.android.app")
	id("convention.android.compose")
	id("plugin.junit")
	id("plugin.dagger")
}

android {
	namespace = "com.boot.playground"
	val projectBuildConfig = com.convention.configs.ProjectBuild.create(infra)
	setupAndroidApplication(
		config = projectBuildConfig,
		versionName = "1.0",
		applicationId = "com.boot.playground",
	)
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
	implementation(projects.platform.designSystem)
	implementation(projects.platform.components)
	implementation(projects.external.appsflyerWrapper)
	addComposeDependencies(project)

	implementation(libs.splashscreen)
	implementation(libs.bundles.coroutine)


	implementation(libs.appcompanist.permission)
	implementation(libs.datastoreCore)
	implementation(libs.datastoreAndroid)
	implementation(libs.activityKtx)
	implementation(libs.activity)
	implementation(libs.lifecycle.process)

	//Playground libraries
	implementation("com.google.android.gms:play-services-ads:21.1.0")
//  implementation("com.google.android.gms:play-services-ads-identifier:18.0.1")

	testImplementation(libs.test.coroutine)
	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
}
