package com.convention.extensions

import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Action
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun LibraryExtension.`kotlinOptions`(configure: Action<KotlinJvmOptions>): Unit =
	(this as org.gradle.api.plugins.ExtensionAware).extensions.configure("kotlinOptions", configure)

internal fun AppExtension.`kotlinOptions`(configure: Action<KotlinJvmOptions>): Unit =
	(this as org.gradle.api.plugins.ExtensionAware).extensions.configure("kotlinOptions", configure)
