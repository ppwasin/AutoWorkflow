enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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

dependencyResolutionManagement {
    versionCatalogs {
        create("build") {
            from(files("gradle/build.versions.toml"))
        }
    }
}