apply<plugin.spotless.SpotlessPlugin>()

plugins {
    application
//    alias(infra.plugins.kotlin.jvm)
    kotlin("jvm")
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("com.boot.backend.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback)
    implementation(libs.kmongo)
}
