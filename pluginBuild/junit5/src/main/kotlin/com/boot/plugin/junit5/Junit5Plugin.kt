package com.boot.plugin.junit5

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

class Junit5Plugin : Plugin<Project> {
    companion object{
        private const val version = "5.7.0"
        private const val junit5platform = "org.junit:junit-bom:$version"
        private const val junit5Jupiter = "org.junit.jupiter:junit-jupiter"
    }

    override fun apply(project: Project) {
        project.tasks.withType(Test::class.java) {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        project.dependencies.run {
            testImplementation(platform(junit5platform))
            testImplementation(junit5Jupiter)
        }
    }
}