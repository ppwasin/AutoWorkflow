package com.boot.scripts.cd.tasks

import com.boot.scripts.cd.internal.ReleaseVersion
import com.boot.scripts.cd.internal.ReleaseVersionParser
import com.boot.scripts.cd.internal.release.ReleaseChannel
import com.boot.scripts.cd.internal.release.ReleaseDistributor
import com.boot.scripts.cd.internal.shell
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class PatchRelease : DefaultTask() {

  @TaskAction
  fun setup() {
    //Get version number
    val versionString = project.property("version").toString()
    
    //Fetch tags from origin
    shell("git fetch --prune --tags")

    // Increase Patch version
    val newVersion =
      versionString
        .run(ReleaseVersionParser::fromString)
        .let(ReleaseVersion::increasePatch)

    // Annotate Version with Tag
    shell("git tag -a $newVersion -m 'Release version $newVersion'")
    shell("git push origin $newVersion")

    // Release
    val distributor = ReleaseDistributor(versionName = newVersion.toString())
    distributor.release(setOf(ReleaseChannel.AppDistribution, ReleaseChannel.Firebase))
  }
}
