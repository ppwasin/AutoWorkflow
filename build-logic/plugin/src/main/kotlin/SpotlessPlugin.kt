import com.diffplug.gradle.spotless.SpotlessExtension
import java.io.File
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import utils.InstallSpotlessPreCommitHook

@Suppress("unused")
class SpotlessPlugin : Plugin<Project> {
  override fun apply(project: Project): Unit = project.run {
    plugins.apply("com.diffplug.spotless")
    val buildDir = buildDir
    configure<SpotlessExtension> {
      ratchetFrom = "origin/main"
      kotlin {
        // by default the target is every '.kt' and '.kts` file in the java sourcesets
        ktfmt().googleStyle().configure {
          it.setMaxWidth(80)
        }
        target("**/*.kt")
        targetExclude("$buildDir/**/*.kt")
        targetExclude("bin/**/*.kt")
      }
    }
    tasks.register<InstallSpotlessPreCommitHook>("installSpotlessHook", rootDir.toString())

  }
}
