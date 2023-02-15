plugins {
	kotlin("jvm")
	id("plugin.junit")
	application
//    alias(infra.plugins.kotlin.jvm)

}

group = "com.boot"
version = "0.0.1"

application {
	mainClass.set("com.boot.backend.ApplicationKt")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(libs.ktor.serialization)
	implementation(libs.ktor.server.core)
	implementation(libs.ktor.server.cio)
	implementation(libs.logback)
	implementation(libs.kmongo)

	implementation(projects.shopping)

	testImplementation(libs.ktor.client.mock)
	testImplementation(libs.test.kotest.junit5)
	testImplementation(libs.test.kotest.assert)
}
