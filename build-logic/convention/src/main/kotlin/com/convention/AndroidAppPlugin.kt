package com.convention

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.convention.configs.ProjectBuild
import com.convention.extensions.kotlinOptions
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidAppPlugin : Plugin<Project> {
	override fun apply(project: Project) {
		with(project.pluginManager) {
			apply("com.android.application")
			apply("kotlin-android")
		}

	}
}

fun BaseAppModuleExtension.setupAndroidApplication(
	config: ProjectBuild,
	versionName: String,
	applicationId: String
) {
	compileSdk = config.compileSdk
	defaultConfig {
		this.applicationId = applicationId
		this.minSdk = config.minSdk
		this.targetSdk = config.targetSdk
		this.versionCode = 1
		this.versionName = versionName
		this.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	compileOptions {
		sourceCompatibility = config.java
		targetCompatibility = config.java
	}
	kotlinOptions {
		jvmTarget = config.java.toString()
	}
}
