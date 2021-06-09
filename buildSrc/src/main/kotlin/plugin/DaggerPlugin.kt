package plugin

import ext.androidTestImplementation
import ext.implementation
import ext.kapt
import ext.kaptAndroidTest
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

private val version = "2.35.1"

class DaggerPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    if (project.plugins.findPlugin("kotin-kapt") == null){
      println("[DaggerPlugin] cannot find kapt => apply kotlin-kapt")
      project.apply(plugin = "kotlin-kapt")
    }


    project.dependencies.run {
      implementation("com.google.dagger:dagger-android-support:${version}")
      kapt("com.google.dagger:dagger-compiler:${version}")
      kapt("com.google.dagger:dagger-android-processor:${version}")

      androidTestImplementation("com.google.dagger:dagger-android-support:${version}")
      kaptAndroidTest("com.google.dagger:dagger-compiler:${version}")
      kaptAndroidTest("com.google.dagger:dagger-android-processor:${version}")
    }
  }
}