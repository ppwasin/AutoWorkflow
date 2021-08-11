package com.boot.scripts.cd.tasks

import com.boot.scripts.cd.internal.ReleaseVersion
import com.boot.scripts.cd.internal.ReleaseVersionParser
import com.boot.scripts.cd.internal.getLastTagString
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

    // Fetch tags from origin
    shell("git fetch --prune --tags")

    // Increase Minor version
    val newVersion =
      getLastTagString(matchRegex = "*.*.*")
        .run(ReleaseVersionParser::fromString)
        .let(ReleaseVersion::increaseMinor)

    // Create RC Branch
    val rcBranchName = "rc-${newVersion.major}.${newVersion.minor}"
    shell("git branch $rcBranchName")
    shell("git push origin $rcBranchName")

    // Annotate Version with Tag
    shell("git tag -a $newVersion -m 'Release version $newVersion'")
    shell("git push origin $newVersion")

    // Release
    val uploader = ReleaseDistributor(versionName = newVersion.toString())
    uploader.release(setOf(ReleaseChannel.AppDistribution, ReleaseChannel.Firebase))
  }
}
