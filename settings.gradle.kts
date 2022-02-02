enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "AutoWorkflow"
includeBuild("pluginBuild")
include(":app")
include(":playground")

include(":platform:navigation")
include(":platform:designSystem")
include(":platform:components")

include(":fake:pagination")

include(":features:entrypoint")
include(":features:recipe")
