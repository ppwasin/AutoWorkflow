plugins {
	`kotlin-dsl`
}

repositories {
	google()
	mavenCentral()
	gradlePluginPortal()
}

dependencies {
	implementation(Deps.Core.androidGradle)
	implementation(Deps.Core.kotlinGradlePlugin)
	implementation(Deps.Spotless.classPath)
}

kotlin {
	// Add Deps to compilation, so it will become available in main project
	sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}