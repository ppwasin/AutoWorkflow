package com.convention.configs

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class VersionCatalogs(project: Project){
    val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
    val infra = project.extensions.getByType<VersionCatalogsExtension>().named("infra")
    inner class Versions(private val libs: VersionCatalog) {
        val compose = libs.findVersion("compose").get().requiredVersion
        val junit5 = libs.findVersion("junit5").get().requiredVersion
        val dagger = libs.findVersion("dagger").get().requiredVersion
    }

    val versions = Versions(libs)
}