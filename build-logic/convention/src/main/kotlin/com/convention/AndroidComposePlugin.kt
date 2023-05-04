package com.convention

import com.android.build.gradle.BaseExtension
import com.convention.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.configure

class AndroidComposePlugin : Plugin<Project> {
	override fun apply(project: Project) {
		project.configure<BaseExtension> {
			buildFeatures.compose = true
			composeOptions {
				kotlinCompilerExtensionVersion = project.libs.versions.compose.get()
			}
			packagingOptions {
				// Multiple dependency bring these files in. Exclude them to enable
				// our test APK to build (has no effect on our AARs)
				resources.excludes.run {
					add("META-INF/AL2.0")
					add("META-INF/LGPL2.1")
				}
			}
		}
	}
}

fun DependencyHandler.addComposeDependencies(project: Project) {
	add("implementation", platform(project.libs.compose.bom))
	add("implementation", project.libs.bundles.compose)
	add("debugImplementation", project.libs.test.composeRule)
	add("debugImplementation", project.libs.compose.uiTooling)
	add("androidTestImplementation", platform(project.libs.compose.bom))
	add("androidTestImplementation", project.libs.androidTest.compose)
}
