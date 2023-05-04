package com.convention.configs

import org.gradle.accessors.dm.LibrariesForInfra
import org.gradle.api.JavaVersion

class ProjectBuild(
	val java: JavaVersion,
	val compileSdk: Int,
	val minSdk: Int,
	val targetSdk: Int
) {
	companion object {
		fun create(infra: LibrariesForInfra): ProjectBuild {
			val versions = infra.versions
			return ProjectBuild(
				java = JavaVersion.toVersion(versions.java.get()),
				compileSdk = versions.compileSdk.get().toInt(),
				minSdk = versions.minSdk.get().toInt(),
				targetSdk = versions.targetSdk.get().toInt(),
			)
		}
	}
}
