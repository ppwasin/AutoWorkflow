package plugin.spotless

import java.io.File
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class InstallSpotlessPreCommitHook : DefaultTask() {
  @TaskAction
  fun installHook() {
    print("CreateSpotlessPreCommitHook Hello")
    val gitHooksDirectory = File("${project.rootDir}/.git/hooks/")
    if (!gitHooksDirectory.exists()) {
      gitHooksDirectory.mkdirs()
    }
    File("${project.rootDir}/.git/hooks", "pre-commit")
        .writeText(
            """
                #!/bin/bash
                echo "Running spotless check"
                ./gradlew spotlessApply
                """.trimIndent())
    Runtime.getRuntime().exec("chmod +x .git/hooks/pre-commit")
  }
}
