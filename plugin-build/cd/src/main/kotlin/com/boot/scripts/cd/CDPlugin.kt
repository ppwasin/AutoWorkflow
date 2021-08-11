package com.boot.scripts.cd
import com.boot.scripts.cd.tasks.MinorRelease
import com.boot.scripts.cd.tasks.PatchRelease
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.kotlin.dsl.register

class CDPlugin : Plugin<Project> {

  override fun apply(target: Project) {
	  check(target === target.rootProject) { "Cannot apply build plugin to subprojects." }
	  
	  target.registerByClassName<MinorRelease>()
	  target.registerByClassName<PatchRelease>()
  }
	
	private inline fun <reified T: Task> Project.registerByClassName() {
		tasks.register<T>(T::class.simpleName!!)
	}
}