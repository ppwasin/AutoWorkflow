package com.boot.entrypoint

import androidx.compose.ui.test.junit4.createComposeRule
import com.boot.entrypoint.screen.EntrypointScreen
import org.junit.Rule
import org.junit.Test

class OnboardingUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun test() {
    composeTestRule.setContent { EntrypointScreen("") }
  }
}
