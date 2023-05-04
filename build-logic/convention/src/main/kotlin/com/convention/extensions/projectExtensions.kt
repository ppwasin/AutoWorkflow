package com.convention.extensions

import org.gradle.accessors.dm.LibrariesForInfra
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project

val Project.libs: LibrariesForLibs get() =
	(this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("libs") as LibrariesForLibs

val Project.infra: LibrariesForInfra get() =
	(this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("infra") as LibrariesForInfra
