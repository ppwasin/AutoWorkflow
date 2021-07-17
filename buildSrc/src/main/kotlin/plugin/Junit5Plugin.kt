package plugin

import ext.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test

class Junit5Plugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.withType(Test::class.java) {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        project.dependencies.run {
            testImplementation(platform(Deps.Test.junit5platform))
            testImplementation(Deps.Test.junit5Jupiter)
        }
    }
}