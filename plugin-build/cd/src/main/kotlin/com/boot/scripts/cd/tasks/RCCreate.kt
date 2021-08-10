package com.boot.scripts.cd.tasks

import com.boot.scripts.cd.internal.getCurrentVersion
import com.boot.scripts.cd.internal.shell
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class RCCreate : DefaultTask() {

  @TaskAction
  fun setup() {
    shell("git fetch --prune --tags")
    val newVersion = getCurrentVersion().let { currentVersion ->
      currentVersion.copy(minor = currentVersion.minor + 1, patch = 0)
    }
    
    // Create RC Branch
    val rcBranchName = "rc-${newVersion.major}.${newVersion.minor}"
    shell("git branch $rcBranchName")
    shell("git push origin $rcBranchName")
    
    // Annotate Version with Tag
    val tagName = "v${newVersion}"
    shell("git tag -a $tagName -m 'Release version $newVersion'")
    shell("git push origin $tagName")
    
  }
}
