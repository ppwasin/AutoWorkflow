package com.modular.plugin.configs

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class Dependencies(project: Project){
    val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
    val infra = project.extensions.getByType<VersionCatalogsExtension>().named("infra")
    inner class Versions(private val libs: VersionCatalog) {
        val compose = libs.findVersion("compose").get().requiredVersion
    }

    val versions = Versions(libs)
}