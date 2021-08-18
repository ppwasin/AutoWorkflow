object Deps {
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
    const val materialIconsExt =
      "androidx.compose.material:material-icons-extended:${Versions.compose}"
    // Tooling support (Previews, etc.)
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

    const val animation = "androidx.compose.animation:animation:${Versions.compose}"
    const val animationCore = "androidx.compose.animation:animation-core:${Versions.compose}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha03"
    const val uiGeometry = "androidx.compose.ui:ui-geometry:${Versions.compose}"
    const val uiGraphic = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val uiText = "androidx.compose.ui:ui-text:${Versions.compose}"
    const val uiUtils = "androidx.compose.ui:ui-util:${Versions.compose}"
    const val uiViewBinding = "androidx.compose.ui:ui-viewbinding:${Versions.compose}"

    // Test
    // Test rules and transitive dependencies:
    const val uiTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    // Needed for createComposeRule, but not createAndroidComposeRule:
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

    // Accompanist
    object Accompanist {
      //built on top of 2.4.0-alpha06
      const val navigationAnimation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
    }
    
    //Nav
    const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNav}"
  }

  object WorkManager {
    const val kotinCoroutine = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    const val optionalGCMNetwokr = "androidx.work:work-gcm:${Versions.workManager}"
    const val androidTest = "androidx.work:work-testing:${Versions.workManager}"
    const val optionalMultiprocess = "androidx.work:work-multiprocess:${Versions.workManager}"
  }

  // Android UI
  const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
  const val material = "com.google.android.material:material:${Versions.material}"

  // Test
  object Test {
    const val junit5platform = "org.junit:junit-bom:${Versions.junit5}"
    const val junit5Jupiter = "org.junit.jupiter:junit-jupiter"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val junitInstrumental = "androidx.test.ext:junit:${Versions.junitInstrumental}"
  }
}
