package com.boot.playground.test.activityidle

import android.app.Activity
import android.app.Application
import android.os.Bundle

class ActivityIdleCallbacks(
  private val onCreated: (Activity) -> Unit,
  private val onDestroyed: (Activity) -> Unit
) : Application.ActivityLifecycleCallbacks {
  override fun onActivityCreated(
    activity: Activity,
    savedInstanceState: Bundle?
  ) {
    onCreated(activity)
  }

  override fun onActivityDestroyed(activity: Activity) {
    onDestroyed(activity)
  }

  override fun onActivityStarted(activity: Activity) = Unit
  override fun onActivityResumed(activity: Activity) = Unit
  override fun onActivityPaused(activity: Activity) = Unit
  override fun onActivityStopped(activity: Activity) = Unit
  override fun onActivitySaveInstanceState(
    activity: Activity,
    outState: Bundle
  ) = Unit
}
