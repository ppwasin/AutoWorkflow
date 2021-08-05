package com.boot.scripts.cd.tasks

import com.lordcodes.turtle.shellRun
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class CreateRC : DefaultTask() {
  @TaskAction
  fun setup() {
    println("Hello From CreateRC")
    //		Runtime.getRuntime().exec("./my_script.sh")
    //		val shellLocation = ShellLocation.HOME.resolve(project.rootDir)
    //		val output = shellRun("git", listOf("status"), shellLocation)
    //		println(output)
    //    println(ShellLocation.CURRENT_WORKING)

    shellRun {
      git.gitCommand(listOf("fetch", "--prune", "--tags"))
      //      val tag = git.gitCommand(listOf("tag", "-l", "-n1"))
      //      val tag = git.gitCommand(listOf("describe", "--abbrev=0", "--tags"))
  
      
      val tag = git.gitCommand("describe --long --first-parent --match \"v[0-9]*\"".split(" "))
      val version = tag.removePrefix("v")
      println(version)
      tag
    }
  }
}
