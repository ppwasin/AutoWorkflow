package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class SpotlessPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.run {
            apply(plugin = Deps.Spotless.pluginId)
            configure<com.diffplug.gradle.spotless.SpotlessExtension> {
                ratchetFrom = "origin/main"
                kotlin {
                    // by default the target is every '.kt' and '.kts` file in the java sourcesets
                    ktfmt()
                    licenseHeaderFile(rootProject.file("spotless/copyright.kt")) // or licenseHeaderFile
                    target("**/*.kt")
                    targetExclude("$buildDir/**/*.kt")
                    targetExclude("bin/**/*.kt")
                }
            }
        }
    }
}