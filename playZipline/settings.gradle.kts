enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
//	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
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
		create("ziplineLib") {
			from(files("../gradle/ziplineLib.versions.toml"))
		}
	}
}

rootProject.name = "playZipline"

include(":presenters")
include(":android")
