import org.gradle.api.JavaVersion

object Deps {
    object Core {
        const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        val java = JavaVersion.VERSION_1_8
    }
    object Spotless {
        private const val version = "5.10.2"
        const val classPath = "com.diffplug.spotless:spotless-plugin-gradle:${version}"
        const val pluginId = "com.diffplug.spotless"
    }

    object Compose {
//        const val version = "1.0.0-beta01"
    }
}
