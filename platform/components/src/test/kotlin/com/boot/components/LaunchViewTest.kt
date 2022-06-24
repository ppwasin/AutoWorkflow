package com.boot.components

import android.widget.LinearLayout
import app.cash.paparazzi.DeviceConfig.Companion.NEXUS_5
import app.cash.paparazzi.DeviceConfig.Companion.NEXUS_5_LAND
import app.cash.paparazzi.DeviceConfig.Companion.PIXEL_3
import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

class LaunchViewTest {
  @get:Rule val paparazzi = Paparazzi(deviceConfig = PIXEL_3)

  @Test
  fun pixel3() {
    val launch = paparazzi.inflate<LinearLayout>(R.layout.launch)
    paparazzi.snapshot(launch)
  }

  @Test
  fun pixel3_differentThemes() {
    paparazzi.unsafeUpdateConfig(theme = "android:Theme.Material.Light")
    var launch = paparazzi.inflate<LinearLayout>(R.layout.launch)
    paparazzi.snapshot(view = launch, name = "light")

    paparazzi.unsafeUpdateConfig(theme = "android:Theme.Material.Light.NoActionBar")
    launch = paparazzi.inflate(R.layout.launch)
    paparazzi.snapshot(view = launch, name = "light no_action_bar")
  }

  @Test
  fun nexus5_differentOrientations() {
    paparazzi.unsafeUpdateConfig(deviceConfig = NEXUS_5)
    var launch = paparazzi.inflate<LinearLayout>(R.layout.launch)
    paparazzi.snapshot(launch, "portrait")

    paparazzi.unsafeUpdateConfig(deviceConfig = NEXUS_5_LAND)
    launch = paparazzi.inflate(R.layout.launch)
    paparazzi.snapshot(launch, "landscape")
  }
}
