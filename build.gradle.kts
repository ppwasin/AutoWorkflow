// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidGradle)
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.GoogleService.classpath)
        classpath(Build.FirebaseAppDistribution.classpath)
        classpath(Build.Spotless.classpath)
        classpath(Build.PlayPublisher.classpath)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
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
}

plugins {
    id("com.boot.scripts.cd.CDPlugin")
}