package com.boot.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class DependenciesPlugin : Plugin<Project> {

    override fun apply(target: Project) {
    }

}

object DepsTest {
    val koinVersion = "3.0.2"
}