package com.boot.scripts.cd.tasks

import com.boot.scripts.cd.internal.getCurrentVersion
import com.boot.scripts.cd.internal.shell
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class RCPatch : DefaultTask() {
  @TaskAction
  fun run() {
    shell("git fetch --prune --tags")
    val newVersion = getCurrentVersion().let { currentVersion ->
      currentVersion.copy(patch = currentVersion.patch + 1)
    }
	  
    val tagName = "v$newVersion"
    shell("git tag -a $tagName -m 'Bump version to $newVersion'")
    shell("git push origin $tagName")
  }
}
