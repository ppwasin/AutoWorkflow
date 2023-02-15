package com.convention

import com.android.build.gradle.LibraryExtension
import com.convention.extensions.setupSdk
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class SimplAndroidLib : Plugin<Project> {
	override fun apply(project: Project) {
		with(project.pluginManager) {
			apply("com.android.library")
			apply("kotlin-android")
		}
		project.configure<LibraryExtension> {
			setupSdk()
		}
	}

}
