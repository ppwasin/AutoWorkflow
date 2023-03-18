package com.boot.playground.test

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.boot.playground.test.coroutineidle.CoroutineIdleRule
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
		composeTestRule.waitForIdle()

		composeTestRule
			.onNode(hasTestTag(TestSecondActivity.TAG_SAMPLE_TEXT))
			.assertExists()
	}
}
