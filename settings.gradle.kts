enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
	repositories {
		google()
		gradlePluginPortal()
		mavenCentral()
		maven("https://oss.sonatype.org/content/repositories/snapshots/")
	}
}

dependencyResolutionManagement {
	versionCatalogs {
		create("infra") {
			from(files("gradle/infra.versions.toml"))
		}
	}
}

rootProject.name = "AutoWorkflow"
// Infra
includeBuild("build-logic")
//includeBuild("playZipline")
//include(":shopping")

// Mobile
include(":app")
include(":playground")
include(":platform:navigation")
include(":platform:designSystem")
include(":platform:components")
include(":platform:sync")
include(":fake:pagination")
include(":features:entrypoint")
include(":features:recipe")
include(":features:payment")
include(":external:appsflyerWrapper")
include(":test:snapshot")

// Backend
//include(":backend")
//include(":dynamicfeature")

include(":platform:dynamicfeature")
include(":dynamicFeatureSample:onDemend")
include(":dynamicFeatureSample:onInstall")
include(":dynamicFeatureSample:consumer")
