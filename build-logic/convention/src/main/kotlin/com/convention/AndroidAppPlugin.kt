package com.convention

import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.convention.configs.ProjectBuild
import com.convention.extensions.configureCompiler
import com.convention.extensions.infra
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidAppPlugin : Plugin<Project> {
	override fun apply(project: Project) = with(project) {
		with(pluginManager) {
			apply("com.android.application")
			apply("kotlin-android")
		}
		val config = ProjectBuild.create(project.infra)
		configureCompiler(config.java)
		configure<AppExtension> {
			buildTypes {
				getByName("release") {
					isMinifyEnabled = true
					isShrinkResources = true
					isDebuggable = false
				}
				getByName("debug") {
					isMinifyEnabled = false
					isShrinkResources = false
					isDebuggable = true
				}
			}
		}
	}
}

fun Project.setupAndroidApplication(
	versionName: String,
	applicationId: String
) {
	val config = ProjectBuild.create(project.infra)
	configure<AppExtension> {
		namespace = applicationId
		setCompileSdkVersion(config.compileSdk)
		defaultConfig {
			this.applicationId = applicationId
			this.minSdk = config.minSdk
			this.targetSdk = config.targetSdk
			this.versionCode = 1
			this.versionName = versionName
			this.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		}
	}
}
