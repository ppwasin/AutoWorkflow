import org.gradle.api.JavaVersion

object Deps {
  object Core {
    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val java = JavaVersion.VERSION_1_8
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

    const val activity = "androidx.activity:activity-compose:1.3.0-alpha03"
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
}
