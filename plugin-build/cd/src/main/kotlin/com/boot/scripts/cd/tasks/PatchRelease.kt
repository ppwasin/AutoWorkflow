package com.boot.scripts.cd.tasks

import com.boot.scripts.cd.internal.ReleaseVersion
import com.boot.scripts.cd.internal.ReleaseVersionExt
import com.boot.scripts.cd.internal.fromString
import com.boot.scripts.cd.internal.getCurrentVersion
import com.boot.scripts.cd.internal.release.ReleaseChannel
import com.boot.scripts.cd.internal.release.ReleaseDistributor
import com.boot.scripts.cd.internal.shell
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class PatchRelease : DefaultTask() {

  @TaskAction
  fun setup() {
    val currentBranch = shell("git branch --show-current")
    check(currentBranch.startsWith("rc-")) { "Must run on release candidate branch (e.g., rc-1.0)" }
    val version = currentBranch.removePrefix("rc-")
      .split(".")
    val majorVersion = version[0]
    val minorVersion = version[1]

    // Fetch tags from origin
    shell("git fetch --prune --tags")

    // Increase Patch version from property version
    val newVersion =
      getCurrentVersion(matchRegex = "$majorVersion.$minorVersion.*")
        .let(ReleaseVersion::increasePatch)

    // Annotate Version with Tag
    shell("git tag -a $newVersion -m 'Release version $newVersion'")
    shell("git push origin $newVersion")

    // Release
    val distributor = ReleaseDistributor(versionName = newVersion.toString())
    distributor.release(setOf(ReleaseChannel.AppDistribution, ReleaseChannel.Firebase))
  }
}
