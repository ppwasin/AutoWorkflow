plugins {
  `kotlin-dsl`
  kotlin("jvm")
  id("java-gradle-plugin")
}

repositories {
  google()
  gradlePluginPortal()
  mavenCentral()
}

gradlePlugin {
  plugins {
    create("flavor-disable-plugin") {
      id = "com.boot.plugin.FlavorDisablePlugin"
      implementationClass = "com.boot.plugin.flavor.FlavorDisablePlugin"
    }
  }
}

dependencies {
  implementation("com.android.tools.build:gradle:7.0.0-beta01") // doesn't work yet
}