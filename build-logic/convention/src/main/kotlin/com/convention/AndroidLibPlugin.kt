package com.convention

import com.android.build.gradle.LibraryExtension
import com.convention.configs.ProjectBuild
import com.convention.extensions.configureCompiler
import com.convention.extensions.infra
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibPlugin : Plugin<Project> {
	override fun apply(project: Project) = with(project) {
		with(pluginManager) {
			apply("com.android.library")
			apply("kotlin-android")
		}
		val config = ProjectBuild.create(project.infra)
		configure<LibraryExtension> {
			compileSdk = config.compileSdk
			defaultConfig {
				minSdk = config.minSdk
				testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
				consumerProguardFiles("consumer-rules.pro")
			}
		}
		configureCompiler(config.java)
	}
}
