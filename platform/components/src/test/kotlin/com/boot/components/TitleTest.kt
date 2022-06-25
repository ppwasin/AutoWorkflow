package com.boot.components

import android.widget.FrameLayout
import android.widget.TextView
import app.cash.paparazzi.Paparazzi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class TitleTest {

  @get:Rule val paparazzi = Paparazzi()

  private lateinit var parentView: FrameLayout
  private lateinit var testNameTextView: TextView

  @Before
  fun setup() {
    parentView = paparazzi.inflate(R.layout.layout_to_test)
    testNameTextView = parentView.findViewById(R.id.testName)
  }

  //  @Test
  //  fun simple() {
  //    paparazzi.snapshot { Title("Random") }
  //  }

  @Test
  fun `Default configuration`() {
    testNameTextView.text = "View test:\nDefault configuration"
    paparazzi.snapshot(parentView)
  }
}
