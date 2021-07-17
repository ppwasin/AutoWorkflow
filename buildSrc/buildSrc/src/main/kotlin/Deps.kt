import org.gradle.api.JavaVersion

object Deps {
  object Build {
    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val java = JavaVersion.VERSION_11
    const val compileSdk = 30
    const val minSdk = 23
    const val targetSdk = 30
  }
  object Spotless {
    const val classPath = "com.diffplug.spotless:spotless-plugin-gradle:${Versions.spotless}"
    const val pluginId = "com.diffplug.spotless"
  }

  object Rx {
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
  }

  object Coroutine {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    const val rx = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:${Versions.coroutine}"
    const val reactive = "org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${Versions.coroutine}"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine}"
  }

  object Compose {
    const val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    // Material Design
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val materialIcon = "androidx.compose.material:material-icons-core:${Versions.compose}"
    const val materialIconsExt = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    // Tooling support (Previews, etc.)
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

    const val animation = "androidx.compose.animation:animation:${Versions.compose}"
    const val animationCore = "androidx.compose.animation:animation-core:${Versions.compose}"
    const val uiTesting = "androidx.compose.ui:ui-test-junit4::${Versions.compose}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha03"
    const val uiGeometry = "androidx.compose.ui:ui-geometry:${Versions.compose}"
    const val uiGraphic = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val uiText = "androidx.compose.ui:ui-text:${Versions.compose}"
    const val uiUtils = "androidx.compose.ui:ui-util:${Versions.compose}"
    const val uiViewBinding = "androidx.compose.ui:ui-viewbinding:${Versions.compose}"
  }

  object WorkManager {
    const val kotinCoroutine = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    const val optionalGCMNetwokr = "androidx.work:work-gcm:${Versions.workManager}"
    const val androidTest = "androidx.work:work-testing:${Versions.workManager}"
    const val optionalMultiprocess = "androidx.work:work-multiprocess:${Versions.workManager}"
  }
  
  //UI
  const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
}
