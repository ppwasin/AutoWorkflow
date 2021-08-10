package com.boot.scripts.cd.tasks

import com.boot.scripts.cd.internal.release.ReleaseDistributor
import com.boot.scripts.cd.internal.release.ReleaseChannel
import com.boot.scripts.cd.internal.getCurrentVersion
import com.boot.scripts.cd.internal.getLastTag
import com.boot.scripts.cd.internal.shell
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class PatchRelease : DefaultTask() {

  @TaskAction
  fun setup() {
    //Fetch tags from origin
    shell("git fetch --prune --tags")

    // Increase Patch version
    val newVersion =
      getLastTag(matchRegex = "*.*.*").let { currentVersion ->
        currentVersion.copy(patch = currentVersion.patch + 1)
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
    val distributor = ReleaseDistributor(versionName = newVersion.toString())
    distributor.release(setOf(ReleaseChannel.AppDistribution, ReleaseChannel.Firebase))
  }
}
