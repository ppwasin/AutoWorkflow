package com.convention.configs

import org.gradle.api.JavaVersion

internal object ProjectBuild {
	val java = JavaVersion.VERSION_11
	const val compileSdk = 33
	const val minSdk = 23
	const val targetSdk = 33
}
