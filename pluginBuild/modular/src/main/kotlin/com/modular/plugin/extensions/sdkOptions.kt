package com.modular.plugin.extensions

import com.android.build.gradle.LibraryExtension
import com.modular.plugin.ProjectBuild

fun LibraryExtension.setupSdk(){
    compileSdk = ProjectBuild.compileSdk
    defaultConfig {
        minSdk = ProjectBuild.minSdk
        targetSdk = ProjectBuild.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    compileOptions {
        sourceCompatibility = ProjectBuild.java
        targetCompatibility = ProjectBuild.java
    }
    kotlinOptions {
        jvmTarget = ProjectBuild.java.toString()
    }
}
