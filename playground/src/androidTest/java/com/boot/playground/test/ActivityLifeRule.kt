package com.boot.playground.test

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context.ACTIVITY_SERVICE
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.rules.TestWatcher
import org.junit.runner.Description

fun <T : Activity> isVisible(
  activityCls: Class<T>
): Boolean {
  val am =
    InstrumentationRegistry.getInstrumentation().targetContext.getSystemService(
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

class ActivityIdleResource<T : Activity>(
  private val activityCls: Class<T>,
  private val application: Application
) : IdlingResource {
  private val counter = CountingIdlingResource(this::class.simpleName)
  private val callback = ActivityIdleCallbacks(
    onCreated = {
      if (it.javaClass == activityCls) {
        counter.increment()
      }
    },
    onDestroyed = {
      if (it.javaClass == activityCls) {
        counter.decrement()
      }
    })

  override fun getName(): String = "ActivityLifeIdlingResource"

  override fun isIdleNow(): Boolean {
    return counter.isIdleNow
  }

  override fun registerIdleTransitionCallback(callback: ResourceCallback?) {
    counter.registerIdleTransitionCallback(callback)
  }

  fun start(){
    application.registerActivityLifecycleCallbacks(callback)
  }
  fun cleanup() {
    application.unregisterActivityLifecycleCallbacks(callback)
  }
}


class ActivityIdleRule<T : Activity>(activityCls: Class<T>) : TestWatcher() {
  private val idleResource = ActivityIdleResource(
    activityCls = activityCls,
    application = ApplicationProvider.getApplicationContext() as Application,
  )

  override fun starting(description: Description) {
    IdlingRegistry.getInstance().register(idleResource)
    idleResource.start()
  }

  override fun finished(description: Description) {
    IdlingRegistry.getInstance().unregister(idleResource)
    idleResource.cleanup()
  }
}
