package com.convention

import com.android.build.api.dsl.DynamicFeatureExtension
import com.android.build.gradle.DynamicFeaturePlugin
import com.android.build.gradle.LibraryExtension
import com.convention.configs.ProjectBuild
import com.convention.extensions.infra
import com.convention.extensions.kotlinOptions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class DynamicFeaturePlugin: Plugin<Project> {
	override fun apply(project: Project) {
		with(project.pluginManager) {
			apply("com.android.dynamic-feature")
			apply("kotlin-android")
		}
		project.configure<DynamicFeatureExtension> {
			setupSdk(ProjectBuild.create(project.infra))
		}
	}

	private fun DynamicFeatureExtension.setupSdk(
		projectBuild: ProjectBuild
	) {
		compileSdk = projectBuild.compileSdk
		defaultConfig {
			minSdk = projectBuild.minSdk

			testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		}
		compileOptions {
			sourceCompatibility = projectBuild.java
			targetCompatibility = projectBuild.java
		}
		kotlinOptions {
			jvmTarget = projectBuild.java.toString()
		}
	}
}
