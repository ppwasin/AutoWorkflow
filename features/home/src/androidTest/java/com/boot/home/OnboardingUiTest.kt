package com.boot.home

import androidx.compose.ui.test.junit4.createComposeRule
import com.boot.home.screen.HomeScreen
import org.junit.Rule
import org.junit.Test

class OnboardingUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun test() {
    composeTestRule.setContent { HomeScreen() }
  }
}
