import com.convention.configs.VersionCatalogs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

class Junit5Plugin : Plugin<Project> {
  override fun apply(project: Project) {
    with(project) {
      tasks.withType(Test::class.java) {
        useJUnitPlatform()
        testLogging { events("passed", "skipped", "failed") }
      }

      val junit5Version = VersionCatalogs(project).versions.junit5
      dependencies.run {
        add("testImplementation", platform("org.junit:junit-bom:$junit5Version"))
        add("testImplementation", "org.junit.jupiter:junit-jupiter")
      }
    }
  }
}