package com.modular.plugin

import com.android.build.gradle.LibraryExtension
import com.modular.plugin.configs.Dependencies
import com.modular.plugin.extensions.setupCompose
import com.modular.plugin.extensions.setupSdk
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.kotlin

class ComposeAndroidLib: Plugin<Project> {
    override fun apply(project: Project) {
        with(project.pluginManager) {
            apply("com.android.library")
            apply("kotlin-android")
        }
        val dependencies = Dependencies(project)
        project.configure<LibraryExtension> {
            setupSdk()
            setupCompose(dependencies.versions.compose)
        }
    }

}