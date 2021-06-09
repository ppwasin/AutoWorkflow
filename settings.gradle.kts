rootProject.name = "AutoWorkflow"
include(":app")
include(":playground")
include(":features:home")
includeBuild("gradlePlugins")

//fun shouldInclude(include: String?, moduleName: String): Boolean {
//    if (include != null) {
//        val regex = java.util.regex.Pattern.compile(
//            include,
//            java.util.regex.Pattern.CASE_INSENSITIVE
//        )
//        return regex.matcher(moduleName).find()
//    }
//    return true
//}
