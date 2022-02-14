pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
	}
	dependencyResolutionManagement {
		versionCatalogs {
			create("build") {
				from(files("../gradle/build.versions.toml"))
			}
		}
	}
}

rootProject.name = "pluginBuild"

include(":cd")
include(":junit5")