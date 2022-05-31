package com.modular.plugin.extensions

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.AppExtension
import org.gradle.api.Action
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun LibraryExtension.`kotlinOptions`(configure: Action<KotlinJvmOptions>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("kotlinOptions", configure)

fun AppExtension.`kotlinOptions`(configure: Action<KotlinJvmOptions>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("kotlinOptions", configure)
