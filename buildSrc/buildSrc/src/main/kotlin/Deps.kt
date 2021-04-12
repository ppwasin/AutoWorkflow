import org.gradle.api.JavaVersion

object Deps {
  object Core {
    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val java = JavaVersion.VERSION_11
  }
  object Spotless {
    private const val version = "5.10.2"
    const val classPath = "com.diffplug.spotless:spotless-plugin-gradle:${version}"
    const val pluginId = "com.diffplug.spotless"
  }

  object Rx {
    private const val rxJavaVersion = "2.2.21"
    private const val rxAndroidVersion = "2.1.1"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${rxAndroidVersion}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${rxJavaVersion}"
  }

  object Coroutine {
    private const val version = "1.4.3"
    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${version}"
    const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${version}"
    const val coroutineRx = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:${version}"
    const val coroutineReactive = "org.jetbrains.kotlinx:kotlinx-coroutines-reactive:${version}"
  }

  object Compose {
    const val coreVersion = "1.0.0-beta03"
    private const val activityVersion = "1.3.0-alpha06"
    const val activity = "androidx.activity:activity-compose:$activityVersion"
    const val ui = "androidx.compose.ui:ui:${coreVersion}"
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    const val foundation = "androidx.compose.foundation:foundation:${coreVersion}"
    // Material Design
    const val material = "androidx.compose.material:material:${coreVersion}"
    const val materialIcon = "androidx.compose.material:material-icons-core:${coreVersion}"
    const val materialIconsExt = "androidx.compose.material:material-icons-extended:${coreVersion}"
    // Tooling support (Previews, etc.)
    const val uiTooling = "androidx.compose.ui:ui-tooling:${coreVersion}"

    const val animation = "androidx.compose.animation:animation:${coreVersion}"
    const val animationCore = "androidx.compose.animation:animation-core:${coreVersion}"
    const val uiTesting = "androidx.compose.ui:ui-test-junit4::${coreVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha03"
    const val uiGeometry = "androidx.compose.ui:ui-geometry:${coreVersion}"
    const val uiGraphic = "androidx.compose.ui:ui-graphics:${coreVersion}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${coreVersion}"
    const val uiText = "androidx.compose.ui:ui-text:${coreVersion}"
    const val uiUtils = "androidx.compose.ui:ui-util:${coreVersion}"
    const val uiViewBinding = "androidx.compose.ui:ui-viewbinding:${coreVersion}"
  }

  object WorkManager {
    private const val version = "2.5.0"
    const val kotinCoroutine = "androidx.work:work-runtime-ktx:$version"
    const val optionalGCMNetwokr = "androidx.work:work-gcm:$version"
    const val androidTest = "androidx.work:work-testing:$version"
    const val optionalMultiprocess = "androidx.work:work-multiprocess:$version"
  }

}
