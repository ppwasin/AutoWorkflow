plugins {
  `kotlin-dsl`
  kotlin("jvm")
  id("java-gradle-plugin")
}

repositories { mavenCentral() }

gradlePlugin {
  plugins {
    create("cd-plugin") {
      id = "com.boot.scripts.cd.CDPlugin"
      implementationClass = "com.boot.scripts.cd.CDPlugin"
    }
  }
}

dependencies {
	implementation("com.lordcodes.turtle:turtle:0.5.0")
}
