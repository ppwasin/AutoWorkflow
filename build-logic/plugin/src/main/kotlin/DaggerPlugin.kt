import extensions.applyPluginIfNotExists
import extensions.infra
import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

@Suppress("unused")
class DaggerPlugin : Plugin<Project> {
	override fun apply(project: Project) {
		project.applyPluginIfNotExists("kotlin-kapt")

		val version = project.libs.versions.dagger.get()
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
