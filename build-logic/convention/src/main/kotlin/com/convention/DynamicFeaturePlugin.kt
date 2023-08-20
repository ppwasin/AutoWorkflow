package com.convention

import com.android.build.api.dsl.DynamicFeatureExtension
import com.convention.configs.ProjectBuild
import com.convention.extensions.configureCompiler
import com.convention.extensions.infra
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class DynamicFeaturePlugin : Plugin<Project> {
	override fun apply(project: Project) = with(project) {
		with(pluginManager) {
			apply("com.android.dynamic-feature")
			apply("kotlin-android")
		}
		configure<DynamicFeatureExtension> {
			setupSdk(ProjectBuild.create(project.infra))
		}
		val javaVersion = JavaVersion.toVersion(infra.versions.java.get())
		configureCompiler(javaVersion)
	}

	private fun DynamicFeatureExtension.setupSdk(
		projectBuild: ProjectBuild
	) {
		compileSdk = projectBuild.compileSdk
		defaultConfig {
			minSdk = projectBuild.minSdk
			testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		}
	}
}
