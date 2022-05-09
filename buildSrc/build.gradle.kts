@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	`kotlin-dsl`
	alias(infra.plugins.buildconfig)
}

repositories {
	google()
	mavenCentral()
	gradlePluginPortal()
}

dependencies {
	implementation(infra.androidGradle)
	implementation(infra.kotlinGradle)
	implementation(infra.spotless)
}

buildConfig {
	forClass(packageName = "com.boot.env", className = "Versions") {
		buildConfigField("String", "junit5", "\"${libs.versions.junit5.get()}\"")
		buildConfigField("String", "dagger", "\"${libs.versions.dagger.get()}\"")
	}
}