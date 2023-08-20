package com.convention

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.convention.configs.ProjectBuild
import com.convention.extensions.configureCompiler
import com.convention.extensions.infra
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidAppPlugin : Plugin<Project> {
	override fun apply(project: Project) = with(project) {
		with(pluginManager) {
			apply("com.android.application")
			apply("kotlin-android")
		}
		val config = ProjectBuild.create(project.infra)
		configureCompiler(config.java)
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
}
