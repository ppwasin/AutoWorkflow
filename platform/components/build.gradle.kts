@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	id("com.convention.android")
	id("com.convention.android-compose")
	id("plugin.junit")
	id("plugin.spotless")
}

configure<JunitPluginConfig> {
	includeJunit4.set(true)
}

////May not needed
//val cleanFailures = tasks.register<Delete>("deleteFailures") {
//    setDelete(layout.projectDirectory.dir("out/failures"))
//}
//tasks.withType<Test>().configureEach {
//    val test = this
//    val isPaparazziTask = gradle.startParameter.taskNames.none { it.contains("Paparazzi") }
//    dependsOn(cleanFailures)
//    doFirst {
//        if (isPaparazziTask) {
//            test.systemProperties["paparazzi.test.record"] = false
//            test.systemProperties["paparazzi.test.verify"] = true
//        }
//    }
//}

android{
	namespace = "com.boot.components"
}

dependencies {
	implementation(projects.platform.designSystem)
	implementation(libs.appcompat)
	implementation(libs.bundles.compose)
	implementation(libs.bundles.coroutine)

	implementation(libs.paging.compose)
	implementation(libs.paging.runtime)

	androidTestImplementation(libs.androidTest.espresso)
	androidTestImplementation(libs.androidTest.junit)
	androidTestImplementation(libs.androidTest.compose)
	debugImplementation(libs.test.composeRule)
	testImplementation(libs.test.composeRule)
}
