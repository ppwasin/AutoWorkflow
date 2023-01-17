package com.boot.playground.test.activityidle

import android.app.Activity
import android.app.Application
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

class ActivityIdleResource<T : Activity>(
  private val activityCls: Class<T>,
  private val application: Application
) : IdlingResource {
  private val counter = CountingIdlingResource(this::class.simpleName)
  private val callback =
    ActivityIdleCallbacks(
      onCreated = {
        if (it.javaClass == activityCls) {
          counter.increment()
        }
      },
      onDestroyed = {
        if (it.javaClass == activityCls) {
          counter.decrement()
        }
      }
    )

  override fun getName(): String = "ActivityLifeIdlingResource"

  override fun isIdleNow(): Boolean {
    return counter.isIdleNow
  }

  override fun registerIdleTransitionCallback(
    callback: IdlingResource.ResourceCallback?
  ) {
    counter.registerIdleTransitionCallback(callback)
  }

  fun start() {
    application.registerActivityLifecycleCallbacks(callback)
  }
  fun cleanup() {
    application.unregisterActivityLifecycleCallbacks(callback)
  }
}
