plugins {
  `kotlin-dsl`
  `java-gradle-plugin`
}

repositories {
  google()
  gradlePluginPortal()
  mavenCentral()
}

gradlePlugin {
  plugins {
    create("junit5-plugin") {
      id = "com.boot.plugin.junit5"
      implementationClass = "com.boot.plugin.junit5.Junit5Plugin"
    }
  }
}

//dependencies {
//  implementation(build.androidGradle)
//}