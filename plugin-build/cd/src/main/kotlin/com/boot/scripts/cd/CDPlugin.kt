package com.boot.scripts.cd
import com.boot.scripts.cd.tasks.CreateRC
import com.boot.scripts.cd.tasks.RCPublish
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class CDPlugin : Plugin<Project> {

  override fun apply(target: Project) {
	  check(target === target.rootProject) { "Cannot apply build plugin to subprojects." }
		target.tasks.register<CreateRC>("CreateRC")
		target.tasks.register<RCPublish>("RCPublish")
  }
}
