package com.boot.playground.test

import android.util.Log
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource

class CoroutineIdlingResourceTask(
  private val mainIdlingResource: IdlingResource,
  private val defaultIdlingResource: IdlingResource,
  private val ioIdlingResource: IdlingResource
) : TestRuleTask {

  override fun beforeActivityLaunched() {
    Log.d("TestLog", "register")
    IdlingRegistry.getInstance().register(mainIdlingResource)
  }

  override fun afterActivityFinished() {
    Log.d("TestLog", "unregister")
    IdlingRegistry.getInstance().unregister(mainIdlingResource)
  }
}
