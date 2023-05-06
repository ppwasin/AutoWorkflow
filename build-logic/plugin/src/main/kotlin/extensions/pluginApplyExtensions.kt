package extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

fun Project.applyPluginIfNotExists(pluginId: String){
	if (plugins.findPlugin(pluginId) == null) {
		println("Apply $pluginId")
		apply(plugin = pluginId)
	}
}
