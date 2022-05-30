package com.modular.plugin.extensions

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.modular.plugin.configs.ProjectBuild

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

fun BaseAppModuleExtension.setupSdk(versionNameOverride: String){
    compileSdk = ProjectBuild.compileSdk
    defaultConfig {
        applicationId = ProjectBuild.appId
        minSdk = ProjectBuild.minSdk
        targetSdk = ProjectBuild.targetSdk
        versionCode = 1
        versionName = versionNameOverride

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = ProjectBuild.java
        targetCompatibility = ProjectBuild.java
    }
    kotlinOptions {
        jvmTarget = ProjectBuild.java.toString()
    }
}
