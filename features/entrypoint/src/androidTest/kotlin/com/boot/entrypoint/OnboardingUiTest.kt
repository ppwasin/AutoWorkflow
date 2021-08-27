package com.boot.entrypoint

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.junit4.createComposeRule
import com.boot.entrypoint.ui.EntrypointScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.junit.Rule
import org.junit.Test

class OnboardingUiTest {
  @get:Rule val composeTestRule = createComposeRule()

  @ExperimentalCoroutinesApi
  @FlowPreview
  @ExperimentalFoundationApi
  @Test
  fun test() {
    composeTestRule.setContent { EntrypointScreen() }
  }
}
