rootProject.name = "AutoWorkflow"
include(":app")
include(":playground")
include(":features:entrypoint")
include(":platform:navigation")
include(":platform:theme")
include(":platform:components")
include(":fake:pagination")

includeBuild("plugin-build")
