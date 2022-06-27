import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.create
import utils.VersionCatalogs

interface JunitPluginConfig {
  val includeJunit4: Property<Boolean>
}
/** Jupiter – the engine to run JUnit 5 tests. Vintage – the engine to run JUnit 4 tests. */
@Suppress("unused")
class JunitPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    val extension = project.extensions.create<JunitPluginConfig>("JunitConfig")

    with(project) {
      tasks.withType(Test::class.java).configureEach {
        useJUnitPlatform()
        testLogging { events("passed", "skipped", "failed") }
      }

      val junit5Version = VersionCatalogs(project).versions.junit5
      dependencies.run {
        add("testImplementation", platform("org.junit:junit-bom:$junit5Version"))
        add("testImplementation", "org.junit.jupiter:junit-jupiter")
      }

      afterEvaluate {
        if (extension.includeJunit4.orNull == true) {
          dependencies.run {
            add("testImplementation", "junit:junit:4.12")
            add("testRuntimeOnly", "org.junit.vintage:junit-vintage-engine")
          }
        }
      }
    }
  }
}
