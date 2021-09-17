rootProject.name = "AutoWorkflow"
includeBuild("plugin-build")
include(":app")
include(":playground")

include(":platform:navigation")
include(":platform:theme")
include(":platform:components")

include(":fake:pagination")

include(":features:entrypoint")
include(":features:recipe")
