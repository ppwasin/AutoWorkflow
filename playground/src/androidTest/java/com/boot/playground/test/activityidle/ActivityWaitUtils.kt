package com.boot.playground.test.activityidle

import android.app.Activity
import android.app.ActivityManager
import android.content.Context.ACTIVITY_SERVICE
import androidx.test.platform.app.InstrumentationRegistry

fun <T : Activity> isVisible(activityCls: Class<T>): Boolean {
  val am =
    InstrumentationRegistry.getInstrumentation()
      .targetContext.getSystemService(
        ACTIVITY_SERVICE,
      ) as ActivityManager
  val visibleActivityName = am.appTasks[0].taskInfo.baseActivity?.className
  return visibleActivityName == activityCls.name
}

fun waitUntil(
  checkInterval: Long = 100L,
  timeout: Long = 5000L,
  waitCondition: () -> Boolean
) {
  val startTime = System.currentTimeMillis()
  while (waitCondition()) {
    Thread.sleep(checkInterval)
    if (System.currentTimeMillis() - startTime >= timeout) {
      throw AssertionError("Wait timout: $timeout milliseconds")
    }
  }
}
