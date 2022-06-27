package utils.hook

import java.io.File
import javax.inject.Inject
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class InstallSpotlessPreCommitHook @Inject constructor(private val rootDirPath: String) :
  DefaultTask() {
  @TaskAction
  fun installHook() {
    val hooksDir = getOrCreateHookDir(rootDirPath)
    File(hooksDir, "pre-commit")
      .writeText(
        """
# compares files that match .gitattributes filter to those actually tracked by git-lfs
diff <(git ls-files ':(attr:filter=lfs)' | sort) <(git lfs ls-files -n | sort) >/dev/null

if [[ '$' -ne 0 ]]; then
  echo >&2 "This remote has detected files committed without using Git LFS. 
  Run 'brew install git-lfs && git lfs install' to install it and re-commit your files.";
  exit 1;
fi
""".trimIndent()
      )
    Runtime.getRuntime().exec("chmod +x .git/hooks/pre-commit")
  }
}
