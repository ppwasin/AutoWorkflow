import com.google.devtools.ksp.gradle.KspExtension
import extensions.applyPluginIfNotExists
import extensions.infra
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class RoomPlugin: Plugin<Project> {
	override fun apply(project: Project): Unit = with(project) {
		val kspPluginId = project.infra.plugins.ksp.get().pluginId
		project.applyPluginIfNotExists(kspPluginId)

		configure<KspExtension> {
			arg("room.schemaLocation", "$projectDir/schemas")
		}

		dependencies.run {
			add("implementation", libs.room.runtime)
			add("implementation", libs.room.ktx)
			add("ksp", libs.room.compiler)
		}
	}
}
