package utils

import java.io.File
import javax.inject.Inject
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class InstallSpotlessPreCommitHook @Inject constructor(
  private val rootDirPath: String
) : DefaultTask() {
  @TaskAction
  fun installHook() {
    val gitHooksDirectory = File("${rootDirPath}/.git/hooks/")
    if (!gitHooksDirectory.exists()) {
      gitHooksDirectory.mkdirs()
    }
    File("${rootDirPath}/.git/hooks", "pre-commit")
      .writeText(
        """
                #!/bin/bash
                echo "Running spotless check"
                ./gradlew spotlessApply
                git add .
                """.trimIndent()
      )
    Runtime.getRuntime().exec("chmod +x .git/hooks/pre-commit")
  }
}
