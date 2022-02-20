enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
    versionCatalogs {
        create("infra") {
            from(files("gradle/infra.versions.toml"))
        }
    }
}

rootProject.name = "AutoWorkflow"
// Infra
includeBuild("pluginBuild")
include(":common")

// Mobile
include(":app")
include(":playground")
include(":platform:navigation")
include(":platform:designSystem")
include(":platform:components")
include(":platform:map")
include(":fake:pagination")
include(":features:entrypoint")
include(":features:recipe")

// Backend
include(":backend:shopping")