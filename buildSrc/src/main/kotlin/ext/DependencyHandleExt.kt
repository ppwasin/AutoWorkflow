package ext
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(notation: String) {
    add("implementation", notation)
}

fun DependencyHandler.androidTestImplementation(notation: String) {
    add("androidTestImplementation", notation)
}

fun DependencyHandler.testImplementation(notation: String) {
    add("testImplementation", notation)
}

fun DependencyHandler.testImplementation(notation: Dependency) {
    add("testImplementation", notation)
}

fun DependencyHandler.kapt(notation: String) {
    add("kapt", notation)
}

fun DependencyHandler.kaptAndroidTest(notation: String) {
    add("kaptAndroidTest", notation)
}

fun DependencyHandler.compileOnly(notation: String) {
    add("compileOnly", notation)
}