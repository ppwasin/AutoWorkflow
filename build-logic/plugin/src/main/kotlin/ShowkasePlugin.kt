import extensions.applyPluginIfNotExists
import extensions.infra
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class ShowkasePlugin: Plugin<Project> {
	override fun apply(project: Project): Unit = with(project) {
		val kspPluginId = project.infra.plugins.ksp.get().pluginId
		project.applyPluginIfNotExists(kspPluginId)

		dependencies.run {
			add("implementation", libs.showkase)
			add("ksp", libs.showkaseProcessor)
		}
	}
}
