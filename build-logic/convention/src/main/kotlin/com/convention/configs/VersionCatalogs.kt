package com.convention.configs

//import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal class VersionCatalogs(project: Project) {
	val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
	val infra = project.extensions.getByType<VersionCatalogsExtension>().named("infra")

	inner class Versions(private val libs: VersionCatalog) {
		val compose = libs.findVersion("compose").get().requiredVersion
	}

	val versions = Versions(libs)
}

//val Project.libs get() =
//	extensions.getByName("libs") as LibrariesForLibs
//val Project.infra get() =
//	extensions.getByName("infra") as LibrariesForInfra
//val Project.libs get() = the<LibrariesForLibs>()

