package utils

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class VersionCatalogs(project: Project){
    val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
    inner class Versions(private val libs: VersionCatalog) {
        val junit5 = libs.findVersion("junit5").get().requiredVersion
        val dagger = libs.findVersion("dagger").get().requiredVersion
    }

    val versions = Versions(libs)
}