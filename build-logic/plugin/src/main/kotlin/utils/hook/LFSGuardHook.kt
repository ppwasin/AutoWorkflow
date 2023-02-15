package utils.hook

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File
import javax.inject.Inject

abstract class LFSGuardHook @Inject constructor(private val rootDirPath: String) : DefaultTask() {
	@TaskAction
	fun installHook() {
		val hooksDir = getOrCreateHookDir(rootDirPath)
		File(hooksDir, "pre-commit")
			.writeText(
				"""
                #!/bin/bash
                echo "Running spotless check"
                ./gradlew spotlessApply
                git add .
                """.trimIndent(),
			)
		Runtime.getRuntime().exec("chmod +x .git/hooks/pre-commit")
	}
}
