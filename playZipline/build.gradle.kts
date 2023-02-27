@file:Suppress("DSL_SCOPE_VIOLATION")

buildscript {
	repositories {
		google()
		mavenCentral()
		gradlePluginPortal()
	}
	dependencies {
		classpath(infra.androidGradle)
		classpath(infra.kotlinGradle)
		classpath(ziplineLib.shadowJar.gradle.plugin)
		classpath(ziplineLib.cklib.gradle.plugin)
		classpath(ziplineLib.zipline.gradle.plugin)
	}
}

plugins {
	alias(ziplineLib.plugins.zipline.plugin) apply false
}
