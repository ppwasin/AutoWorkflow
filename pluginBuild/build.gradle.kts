plugins {
	alias(infra.plugins.kotlin.jvm)
}

allprojects {
	repositories {
		google()
		mavenCentral()
	}
}