
plugins {
  `kotlin-dsl`
  `java-gradle-plugin`
//  alias(infra.plugins.buildconfig)
}

repositories {
  google()
  gradlePluginPortal()
  mavenCentral()
}

gradlePlugin {
  plugins {
    create("android-lib-module") {
      id = "com.convention.simpleandroidlib"
      implementationClass = "com.convention.SimpleAndroidLib"
    }
    create("compose-lib-module") {
      id = "com.convention.composeandroidlib"
      implementationClass = "com.convention.ComposeAndroidLib"
    }
    create("kotlin-lib-module") {
      id = "com.convention.kotlin"
      implementationClass = "com.convention.KotlinLib"
    }

    // Plugins
    create("extension-module") {
      id = "com.convention.extension"
      implementationClass = "com.convention.ExtensionPlugin"
    }
  }
}

dependencies {
  implementation(infra.kotlinGradle)
  implementation(infra.androidGradle)
}

//buildConfig {
//  buildConfigField("String", "COMPILE_SDK", "\"${project.name}\"")
//  buildConfigField("String", "APP_VERSION", provider { "\"${project.version}\"" })
//  buildConfigField("String", "APP_SECRET", "\"Z3JhZGxlLWphdmEtYnVpbGRjb25maWctcGx1Z2lu\"")
//  buildConfigField("long", "BUILD_TIME", "${System.currentTimeMillis()}L")
//  buildConfigField("boolean", "FEATURE_ENABLED", "${true}")
//  buildConfigField("IntArray", "MAGIC_NUMBERS", "intArrayOf(1, 2, 3, 4)")
//  buildConfigField("com.github.gmazzo.SomeData", "MY_DATA", "new SomeData(\"a\",1)")
//}