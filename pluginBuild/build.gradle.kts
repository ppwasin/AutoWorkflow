@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	alias(infra.plugins.kotlin.jvm)
}

allprojects {
	repositories {
		google()
		mavenCentral()
	}
}