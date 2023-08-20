package com.convention

import com.convention.extensions.configureCompiler
import com.convention.extensions.infra
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinLibPlugin : Plugin<Project> {
	override fun apply(project: Project) {
		with(project) {
			with(pluginManager) {
				apply("kotlin")
				apply(infra.plugins.kotlin.jvm.get().pluginId)
			}
			val javaVersion = JavaVersion.toVersion(infra.versions.java.get())
			configureCompiler(javaVersion)
		}

	}
}
