enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		gradlePluginPortal()
		mavenCentral()
	}
	versionCatalogs {
		create("libs") {
			from(files("../gradle/libs.versions.toml"))
		}
		create("infra") {
			from(files("../gradle/infra.versions.toml"))
		}
	}
}

rootProject.name = "build-logic"

include(":cd")
include(":plugin")
include(":convention")
