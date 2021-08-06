package com.boot.scripts.cd.tasks

import com.boot.scripts.cd.internal.shell
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class RCDistribute : DefaultTask() {
  @TaskAction
  fun buildAndRelease() {
	  val appModule = "app:"
    shell("./gradlew ${appModule}bundleRelease")
    shell("./gradlew ${appModule}appDistributionUploadRelease")
    shell("./gradlew ${appModule}publishReleaseBundle --track 'alpha'")
  }
}
