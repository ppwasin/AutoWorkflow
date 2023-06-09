import com.google.devtools.ksp.gradle.KspExtension
import extensions.applyPluginIfNotExists
import extensions.infra
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AutoServicePlugin: Plugin<Project> {
	override fun apply(project: Project): Unit = with(project) {
		val kspPluginId = project.infra.plugins.ksp.get().pluginId
		project.applyPluginIfNotExists(kspPluginId)

		configure<KspExtension> {
			arg("autoserviceKsp.verify", "true")
			arg("autoserviceKsp.verbose", "true")
		}

		dependencies.run {
//			add("compileOnly", libs.googleServiceAutoAnnotations)
//			add("ksp", libs.googleServiceAuto)
			add("implementation", libs.googleServiceAutoAnnotations)
			add("ksp", "dev.zacsweers.autoservice:auto-service-ksp:1.0.0")
		}
	}
}
