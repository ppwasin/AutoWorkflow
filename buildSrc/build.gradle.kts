plugins {
	`kotlin-dsl`
	alias(build.plugins.buildconfig)
}

repositories {
	google()
	mavenCentral()
	gradlePluginPortal()
}

dependencies {
	implementation(build.androidGradle)
	implementation(build.kotlinGradle)
	implementation(build.spotless)
}

buildConfig {
	forClass(packageName = "com.boot.env", className = "Versions") {
		buildConfigField("String", "junit5", "\"${libs.versions.junit5.get()}\"")
		buildConfigField("String", "dagger", "\"${libs.versions.dagger.get()}\"")
	}
}