package com.convention

import com.android.build.gradle.BaseExtension
import com.convention.configs.VersionCatalogs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidComposePlugin : Plugin<Project> {
	override fun apply(project: Project) {
		val versionCatalogs = VersionCatalogs(project)
		project.configure<BaseExtension> {
			buildFeatures.compose = true
			composeOptions {
				kotlinCompilerExtensionVersion = versionCatalogs.versions.compose
//				kotlinCompilerExtensionVersion = project.libs.versions.compose.get()
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
