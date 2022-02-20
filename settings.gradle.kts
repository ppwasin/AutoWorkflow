enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
    versionCatalogs {
        create("infra") {
            from(files("gradle/infra.versions.toml"))
        }
    }
}

rootProject.name = "AutoWorkflow"
includeBuild("pluginBuild")
include(":app")
include(":playground")

include(":platform:navigation")
include(":platform:designSystem")
include(":platform:components")
include(":platform:map")

include(":fake:pagination")

include(":features:entrypoint")
include(":features:recipe")

include(":common")