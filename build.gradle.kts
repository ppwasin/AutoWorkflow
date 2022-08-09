import com.convention.configs.ProjectBuild
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
  tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).configureEach {
    kotlinOptions {
      jvmTarget = ProjectBuild.java.toString()
    }
    kotlinOptions.freeCompilerArgs +=
      listOf(
        "-opt-in=kotlin.RequiresOptIn",
        "-opt-in=kotlinx.coroutines.FlowPreview",
        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
        "-Xcontext-receivers",
      )
  }
}

// @Suppress("DSL_SCOPE_VIOLATION")
plugins {
  id("com.boot.scripts.cd.CDPlugin")
  id("com.convention.extension")
  id("com.android.dynamic-feature") version "7.2.1" apply false
  id("org.jetbrains.kotlin.android") version "1.7.0" apply false // Apply on all sub-project. So they can use extensions from com.convention

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
  .copyTo(target = rootDir.resolve("build-logic").resolve("gradle.properties"), overwrite = true)

allprojects {
  repositories {
    google()
    mavenCentral()
  }
}
