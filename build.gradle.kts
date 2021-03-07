// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath(Deps.Core.androidGradle)
        classpath(Deps.Core.kotlinGradlePlugin)
        classpath(Deps.Spotless.classPath)
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://plugins.gradle.org/m2/") }
    }

//    apply<plugin.SpotlessPlugin>()
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}