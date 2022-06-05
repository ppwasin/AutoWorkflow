enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
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
include(":shopping")

// Mobile
include(":app")
include(":playground")
include(":platform:navigation")
include(":platform:designSystem")
include(":platform:components")
include(":fake:pagination")
include(":features:entrypoint")
include(":features:recipe")

// Backend
include(":backend")