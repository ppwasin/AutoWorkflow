package com.boot.playground.test.activityidle

import android.app.Activity
import android.app.Application
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import org.junit.rules.TestWatcher
import org.junit.runner.Description

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
