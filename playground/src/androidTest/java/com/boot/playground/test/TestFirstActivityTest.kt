package com.boot.playground.test

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.View
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.espresso.UiController
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.boot.playground.test.coroutineidle.CoroutineIdleRule
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class TestFirstActivityTest {
	@get:Rule
	val composeTestRule = createEmptyComposeRule()
	@get:Rule
	val coroutineIdleRule = CoroutineIdleRule()

	@Test
	fun testArriveToActivity2() {
		val appContext = InstrumentationRegistry.getInstrumentation().targetContext
		val intent = Intent(appContext, TestFirstActivity::class.java)
		intent.flags = FLAG_ACTIVITY_NEW_TASK
		appContext.startActivity(intent)

//		Espresso.onView(ViewMatchers.isRoot()).perform(waitForActivity(TestSecondActivity::class.java))
		composeTestRule.waitForIdle()

		composeTestRule
			.onNode(hasTestTag(TestSecondActivity.TAG_SAMPLE_TEXT))
			.assertExists()
	}

	private fun waitForActivity(activityClass: Class<*>) = object : androidx.test.espresso.ViewAction {
		override fun getConstraints(): Matcher<View> {
			return ViewMatchers.isRoot()
		}

		override fun getDescription(): String {
			return "Wait for activity ${activityClass.simpleName}"
		}

		override fun perform(uiController: UiController?, view: View?) {
			uiController?.loopMainThreadUntilIdle()
			val targetActivityFound = ActivityLifecycleMonitorRegistry.getInstance()
				.getActivitiesInStage(Stage.RESUMED)
				.any { it.javaClass == activityClass }

			if (!targetActivityFound) {
				uiController?.loopMainThreadUntilIdle()
			}
		}
	}
}
