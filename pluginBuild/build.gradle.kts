plugins {
	alias(build.plugins.kotlin.jvm)
}

allprojects {
	repositories {
		google()
		mavenCentral()
	}
}