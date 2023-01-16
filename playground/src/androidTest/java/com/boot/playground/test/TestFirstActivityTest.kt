package com.boot.playground.test

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class TestFirstActivityTest {
  // create a compose test rule
  @get:Rule val composeTestRule = createEmptyComposeRule()
  val testDispatcher = StandardTestDispatcher()
  private lateinit var ruleTask: TestRuleTask

  @Before
  fun setup() {
    with(DispatcherProvider) {
      ruleTask =
        CoroutineIdlingResourceTask(
          IdlingResourceDispatcher(testDispatcher).apply {
            dispatcherMain = this
          },
          IdlingResourceDispatcher(dispatcherIO),
          IdlingResourceDispatcher(dispatcherDefault),
        )
    }

    ruleTask.beforeActivityLaunched()

    //    DispatcherProvider.dispatcherMain = testDispatcher
  }

  @After
  fun clenup() {
    //    ruleTask.afterActivityFinished()
  }

  @Test
  fun testArriveToActivity2() {
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    val intent = Intent(appContext, TestFirstActivity::class.java)
    intent.flags = FLAG_ACTIVITY_NEW_TASK
    appContext.startActivity(intent)
    composeTestRule.waitForIdle()

    //    testDispatcher.scheduler.advanceUntilIdle()
    composeTestRule
      .onNode(hasTestTag(TestSecondActivity.TAG_SAMPLE_TEXT))
      .assertExists()
  }
}
