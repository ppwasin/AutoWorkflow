package com.boot.scripts.cd.tasks

import com.boot.scripts.cd.internal.shell
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class CreateRC : DefaultTask() {
  companion object {
    const val MAX_ATTEMPT_VERSION_INCREMENT = 5
  }
  @TaskAction
  fun setup() {
    println("Hello From CreateRC")

    // Parse version
    val prefix = "v"
    val tag = shell("git describe --tags --abbrev=0 --first-parent --match '$prefix*'")()
    println("tag: $tag")
    val versions = tag.removePrefix(prefix).split(".")
    val major = versions[0].toInt()
    val minor = versions[1].toInt()
    val patch = versions[2].toInt()
    println("versions: $versions")

    // TODO: What to do when tag/branch already exist => Increase minor
    // update tag version
    var newMinor = minor
    for (attempt in 1..MAX_ATTEMPT_VERSION_INCREMENT) {
      newMinor += 1
      val newVersion = "$major.$newMinor.0"
      val rcTag = "rc$newVersion"

      try {
        // create new tag
        shell("git tag -a $rcTag -m 'Release Candidate for $newVersion'")

        // create rc branch
        shell("git checkout -b $rcTag")
      } catch (error: Exception) {
        println("Error: $error, Current attempt: $attempt (Max: $MAX_ATTEMPT_VERSION_INCREMENT)")
      }
      break
    }

    //    val vHash = shell("git rev-parse --short $v")()
    //    println("v-hash: $vHash")
    // git show-ref v0.4.1

    // increase minor version

    //    shellRun("git", listOf("describe", "--tags", "--long", "--first-parent", "--match", "v*"))
    //      .also(::println)
    //    shellRun {
    //      // git fetch --all --tags
    //      //      git.gitCommand(listOf("fetch", "--prune", "--tags"))
    //      //      val tag = git.gitCommand(listOf("tag", "-l", "-n1"))
    //      //      val tag = git.gitCommand(listOf("describe", "--abbrev=0", "--tags"))
    //
    //      val tag = git.gitCommand("describe --tags --long --first-parent --match \"v*\"")
    //      val version = tag.removePrefix("v")
    //      println(version)
    //      tag
    //    }
  }
}
