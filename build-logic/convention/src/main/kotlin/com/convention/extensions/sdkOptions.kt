package com.convention.extensions

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.convention.configs.ProjectBuild

internal fun LibraryExtension.setupSdk() {
	compileSdk = ProjectBuild.compileSdk
	defaultConfig {
		minSdk = ProjectBuild.minSdk

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}
	compileOptions {
		sourceCompatibility = ProjectBuild.java
		targetCompatibility = ProjectBuild.java
	}
	kotlinOptions {
		jvmTarget = ProjectBuild.java.toString()
	}
}

internal fun BaseAppModuleExtension.setupSdk(
	versionName: String,
	applicationId: String
) {
	compileSdk = ProjectBuild.compileSdk
	defaultConfig {
		this.applicationId = applicationId
		this.minSdk = ProjectBuild.minSdk
		this.targetSdk = ProjectBuild.targetSdk
		this.versionCode = 1
		this.versionName = versionName
		this.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	compileOptions {
		sourceCompatibility = ProjectBuild.java
		targetCompatibility = ProjectBuild.java
	}
	kotlinOptions {
		jvmTarget = ProjectBuild.java.toString()
	}
}
