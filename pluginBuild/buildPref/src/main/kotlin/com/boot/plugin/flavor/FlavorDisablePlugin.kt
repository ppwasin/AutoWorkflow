package com.boot.plugin.flavor

import com.android.build.api.extension.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class FlavorDisablePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val androidComponents = project.extensions.getByType(
            AndroidComponentsExtension::class.java
        )

        androidComponents.beforeVariants {
            if(it.name == "release"){
                it.enabled = false
            }
        }
    }

}

