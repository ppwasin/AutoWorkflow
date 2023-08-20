package com.convention.extensions

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun Project.configureCompiler(javaVersion: JavaVersion) {
	kotlinExtension.jvmToolchain {
		languageVersion.set(JavaLanguageVersion.of(javaVersion.toString()))
	}
	configure<JavaPluginExtension> {
		sourceCompatibility = javaVersion
		targetCompatibility = javaVersion
		toolchain {
			languageVersion.set(JavaLanguageVersion.of(javaVersion.toString()))
		}
	}
	tasks.withType<KotlinCompile>().configureEach {
		kotlinOptions {
			jvmTarget = javaVersion.toString()
			freeCompilerArgs = freeCompilerArgs + listOf(
				"-opt-in=kotlin.RequiresOptIn",
				"-opt-in=kotlinx.coroutines.FlowPreview",
				"-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
				"-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
				"-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
				"-Xcontext-receivers",
			)
		}
	}

	val ext = extensions.findByType(KotlinAndroidProjectExtension::class.java)
	if (ext != null) {
		extensions.configure<KotlinAndroidProjectExtension> {
			jvmToolchain {
				languageVersion.set(JavaLanguageVersion.of(javaVersion.toString()))
			}
		}
	}
}
