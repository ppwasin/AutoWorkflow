package com.boot.scripts.cd.tasks

import com.boot.scripts.cd.internal.ReleaseVersion
import com.boot.scripts.cd.internal.getCurrentVersion
import com.boot.scripts.cd.internal.release.ReleaseChannel
import com.boot.scripts.cd.internal.release.ReleaseDistributor
import com.boot.scripts.cd.internal.shell
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class MinorRelease : DefaultTask() {

  @TaskAction
  fun setup() {
    // Guard Environment
    val currentBranch = shell("git branch --show-current")
    check(currentBranch == "main") { "Must run on 'main' branch" }
    println("Current running branch is: $currentBranch")

    println("Fetch tags from origin")
    shell("git fetch --unshallow --tags")

    // Increase Minor version
    println("### Start increase minor version ###")
    val newVersion =
      getCurrentVersion(matchRegex = "*.*.*")
        .also { println("Got current version: $it") }
        .let(ReleaseVersion::increaseMinor)
        .also { println("Increase to $it") }

    // Create RC Branch
    val rcBranchName = "rc-${newVersion.major}.${newVersion.minor}"
    println("### Create RC branch: $rcBranchName ###")
    shell("git branch $rcBranchName")
    shell("git push origin $rcBranchName")

    // Annotate Version with Tag
    println("### Create Tag: $newVersion ###")
    shell("git tag -a $newVersion -m 'Release version $newVersion'")
    shell("git push origin $newVersion")

    // Release
    println("### Release ###")
    val uploader = ReleaseDistributor(versionName = newVersion.toString())
    uploader.release(setOf(ReleaseChannel.AppDistribution, ReleaseChannel.Firebase))
  }
}
