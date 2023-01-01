package com.boot.components

import app.cash.paparazzi.Paparazzi
import org.junit.Rule
import org.junit.Test

internal class TitleTest {
  @get:Rule val paparazzi = Paparazzi(maxPercentDifference = 0.1)
  @Test
  fun simple() {
    paparazzi.snapshot { Title("Random") }
  }
}
