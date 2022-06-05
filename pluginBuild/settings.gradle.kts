enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
	}
	dependencyResolutionManagement {
		versionCatalogs {
			create("infra") {
				from(files("../gradle/infra.versions.toml"))
			}
		}
	}
}

rootProject.name = "pluginBuild"

include(":cd")
include(":junit5")
include(":convention")