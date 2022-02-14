//val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
//val plugins = extensions.getByType<VersionCatalogsExtension>().named("libs") as

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(build.androidGradle)
        classpath(build.kotlinGradle)
        classpath(build.googleServices)
        classpath(build.firebaseAppdistribution)
        classpath(build.spotless)
        classpath(build.playPublisher)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class) {
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.FlowPreview"
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
    }
}

plugins {
    id("com.boot.scripts.cd.CDPlugin")
}