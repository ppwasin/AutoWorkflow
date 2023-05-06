import extensions.infra
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

@Suppress("unused")
class DaggerPlugin : Plugin<Project> {
	override fun apply(project: Project) {
		val kspPluginId = project.infra.plugins.ksp.get().pluginId
		if (project.plugins.findPlugin(kspPluginId) == null) {
			println("[DaggerPlugin] cannot find ksp => apply $kspPluginId")
			project.apply(plugin = kspPluginId)
		}

		val version = project.libs.versions.dagger.get()
		project.dependencies.run {
			add("implementation", "com.google.dagger:dagger-android-support:${version}")
			add("ksp", "com.google.dagger:dagger-compiler:${version}")
			add("ksp", "com.google.dagger:dagger-android-processor:${version}")

			add("androidTestImplementation", "com.google.dagger:dagger-android-support:${version}")
			add("kspAndroidTest", "com.google.dagger:dagger-compiler:${version}")
			add("kspAndroidTest", "com.google.dagger:dagger-android-processor:${version}")
		}
	}
}
