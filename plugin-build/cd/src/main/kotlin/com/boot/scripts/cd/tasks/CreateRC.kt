package com.boot.scripts.cd.tasks

import com.boot.scripts.cd.internal.getLastTag
import com.boot.scripts.cd.internal.shell
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class CreateRC : DefaultTask() {

  @TaskAction
  fun setup() {
    println("Hello From CreateRC")
    
    //Fetch Tag
    shell("git fetch --prune --tags")

    val currentVersion = getLastTag(matchRegex = "v*.*.0")
    println(currentVersion)
    val newVersion = currentVersion.copy(patch = 0).asDotVersion()
    val tagName = "v$newVersion"
    val rcBranch = "rc-${currentVersion.asDotVersion()}"

    // Push RC Branch
    shell("git checkout -b $rcBranch")
    shell("git push origin $rcBranch")

    // Back to Develop
    shell("git checkout develop")
    // Bump Version
    shell("git tag -a $tagName -m 'Bump version to $newVersion'")
    shell("git push origin $tagName")

    // Back To RC
    shell("git checkout $rcBranch")
    // Release from RC branch
    shell("./gradlew app:bundleRelease")
    shell("./gradlew app:appDistributionUploadRelease")
    shell("./gradlew app:publishReleaseBundle --track 'alpha' --release-name '$currentVersion'")
    println("apply 0.2.3")
  }
}
