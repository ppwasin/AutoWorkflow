package com.boot.playground.test

import android.app.Activity
import android.app.ActivityManager
import android.content.Context.ACTIVITY_SERVICE
import androidx.test.espresso.IdlingResource
import androidx.test.platform.app.InstrumentationRegistry

inline fun <reified T : Activity> isVisible(): Boolean {
  val am =
    InstrumentationRegistry.getInstrumentation().targetContext.getSystemService(
      ACTIVITY_SERVICE,
    ) as ActivityManager
  val visibleActivityName = am.appTasks[0].taskInfo.baseActivity?.className
  return visibleActivityName == T::class.java.name
}

inline fun <reified T : Activity> waitUntilActivityVisible(
  checkInterval: Long = 100L,
  timeout: Long = 5000L
) {
  val startTime = System.currentTimeMillis()
  while (!isVisible<T>()) {
    Thread.sleep(checkInterval)
    if (System.currentTimeMillis() - startTime >= timeout) {
      throw AssertionError("Activity ${T::class.java.simpleName} not visible after $timeout milliseconds")
    }
  }
}

class ActivityLifeIdlingResource<T : Activity>(
  private val activityCls: T
) : IdlingResource {
  override fun getName(): String = this::class::simpleName.toString()

  override fun isIdleNow(): Boolean {
    TODO("Not yet implemented")
  }

  override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
    TODO("Not yet implemented")
  }

}
