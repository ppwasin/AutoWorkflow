object Versions {
  const val kotlin = "1.5.31" // https://kotlinlang.org/docs/releases.html#release-details
  const val ksp = "1.5.31-1.0.1" // https://github.com/google/ksp
  const val androidGradle = "7.0.3"
  const val spotless = "6.0.0" // https://github.com/diffplug/spotless/tree/main/plugin-gradle
  const val rxjava = "2.2.21"
  const val rxAndroid = "2.1.1"
  const val coroutine = "1.5.2" // https://github.com/Kotlin/kotlinx.coroutines
  const val workManager = "2.5.0"
  const val dagger = "2.40.1" // https://github.com/google/dagger

  /** Compose */
  const val compose = "1.1.0-beta03" // https://developer.android.com/jetpack/androidx/releases/compose
  const val composeConstraintLayout = "1.0.0-rc01" // https://developer.android.com/jetpack/androidx/releases/constraintlayout
  const val composeActivity = "1.4.0" // https://developer.android.com/jetpack/androidx/releases/activity
  const val composePaging = "1.0.0-alpha14" // https://developer.android.com/jetpack/androidx/releases/paging
  const val accompanist = "0.21.2-beta" // https://github.com/google/accompanist
  const val composeNav = "2.4.0-beta02" // https://developer.android.com/jetpack/androidx/releases/navigation
  const val composeCoil = "1.4.0" // https://coil-kt.github.io/coil/compose/

  /** Android Platform */
  /* Beware!! DFM + Compose issue in old version
  // Alpha for propagate ViewTreeLifecycleOwner from main to dynamic feature (Compose)
  const val appCompat = "1.3.0-alpha02"
  const val activity = "1.2.0-alpha08"
  const val fragment = "1.3.0-alpha08"
   */
  const val appCompat = "1.3.0"
  const val material = "1.4.0"
  const val paging = "3.0.1"
  const val room = "2.3.0"

  /** Test */
  const val junit5 = "5.7.0"
  const val junitInstrumental = "1.1.3"
  const val espresso = "3.4.0"

  /** Build */
  const val googleService = "4.3.8"
  const val playPublisher = "3.6.0"

  /** Network*/
  const val retrofit = "1.6.3"
  const val okhttp = "4.9.1"

  /** Architecture **/
  const val arrowkt = "1.0.0"
}
