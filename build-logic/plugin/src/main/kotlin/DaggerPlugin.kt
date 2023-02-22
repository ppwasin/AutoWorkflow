import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import utils.VersionCatalogs

@Suppress("unused")
class DaggerPlugin : Plugin<Project> {
	override fun apply(project: Project) {
		if (project.plugins.findPlugin("kotin-kapt") == null) {
			println("[DaggerPlugin] cannot find kapt => apply kotlin-kapt")
			project.apply(plugin = "kotlin-kapt")
		}

		val version = VersionCatalogs(project).versions.dagger
		println("dagger")
		project.dependencies.run {
			add("implementation", "com.google.dagger:dagger-android-support:${version}")
			add("kapt", "com.google.dagger:dagger-compiler:${version}")
			add("kapt", "com.google.dagger:dagger-android-processor:${version}")

			add("androidTestImplementation", "com.google.dagger:dagger-android-support:${version}")
			add("kaptAndroidTest", "com.google.dagger:dagger-compiler:${version}")
			add("kaptAndroidTest", "com.google.dagger:dagger-android-processor:${version}")
		}
	}
}
