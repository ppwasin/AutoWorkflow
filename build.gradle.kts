// val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
// val plugins = extensions.getByType<VersionCatalogsExtension>().named("libs") as

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath(infra.androidGradle)
    classpath(infra.kotlinGradle)
    classpath(infra.googleServices)
    classpath(infra.firebaseAppdistribution)
    classpath(infra.spotless)
    classpath(infra.playPublisher)
    classpath(libs.sqldelight)
  }
}

subprojects {
  tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class) {
    kotlinOptions {
      jvmTarget = Build.java.toString()
    }
    kotlinOptions.freeCompilerArgs +=
      listOf(
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xopt-in=kotlinx.coroutines.FlowPreview",
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xopt-in=androidx.compose.material3.ExperimentalMaterial3Api",
      )
  }
}

// @Suppress("DSL_SCOPE_VIOLATION")
plugins {
  id("com.boot.scripts.cd.CDPlugin")

  //    // this is necessary to avoid the plugins to be loaded multiple times
  //    // in each subproject's classloader
  //    kotlin("jvm") apply false
  //    kotlin("multiplatform") apply false
  //    kotlin("android") apply false
  //    id("com.android.application") apply false
  //    id("com.android.library") apply false
}

rootDir
  .resolve("gradle.properties")
  .copyTo(target = rootDir.resolve("buildSrc").resolve("gradle.properties"), overwrite = true)
  .copyTo(target = rootDir.resolve("pluginBuild").resolve("gradle.properties"), overwrite = true)

allprojects {
  repositories {
    google()
    mavenCentral()
  }
}
