plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("dependencies") {
            id = "com.boot.gradle.dependencies"
            implementationClass = "com.boot.gradle.DependenciesPlugin"
        }
    }
}