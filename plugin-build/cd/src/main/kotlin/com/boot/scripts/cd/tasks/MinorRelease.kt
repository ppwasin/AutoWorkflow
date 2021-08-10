package com.boot.scripts.cd.tasks

import com.boot.scripts.cd.internal.release.ReleaseDistributor
import com.boot.scripts.cd.internal.release.ReleaseChannel
import com.boot.scripts.cd.internal.getCurrentVersion
import com.boot.scripts.cd.internal.getLastTag
import com.boot.scripts.cd.internal.shell
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class MinorRelease : DefaultTask() {

  @TaskAction
  fun setup() {
    //Fetch tags from origin
    shell("git fetch --prune --tags")

    // Increase Minor version
    val newVersion =
      getLastTag(matchRegex = "*.*.*").let { currentVersion ->
        currentVersion.copy(minor = currentVersion.minor + 1, patch = 0)
      }

    // Create RC Branch
    val rcBranchName = "rc-${newVersion.major}.${newVersion.minor}"
    shell("git branch $rcBranchName")
    shell("git push origin $rcBranchName")

    // Annotate Version with Tag
    val tagName = "$newVersion"
    shell("git tag -a $tagName -m 'Release version $newVersion'")
    shell("git push origin $tagName")

    // Release
    val uploader = ReleaseDistributor(versionName = newVersion.toString())
    uploader.release(setOf(ReleaseChannel.AppDistribution, ReleaseChannel.Firebase))
  }
}
