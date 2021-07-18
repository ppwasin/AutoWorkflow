plugins {
	`kotlin-dsl`
}

repositories {
	google()
	mavenCentral()
	gradlePluginPortal()
}

dependencies {
	implementation(Build.androidGradle)
	implementation(Build.kotlinGradlePlugin)
	implementation(Build.Spotless.classpath)
}

kotlin {
	// Add Deps to compilation, so it will become available in main project
	sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}