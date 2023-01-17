package com.boot.playground.test.coroutineidle

import android.util.Log
import androidx.test.espresso.IdlingRegistry
import com.boot.playground.test.DispatcherProvider
import com.boot.playground.test.TestActivityDependencies
import kotlinx.coroutines.Dispatchers
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class CoroutineIdleRule : TestWatcher() {

  private val dispatcherProvider =
    DispatcherProvider(
      dispatcherMain = IdlingResourceDispatcher(Dispatchers.Main),
      dispatcherIO = IdlingResourceDispatcher(Dispatchers.IO),
      dispatcherDefault = IdlingResourceDispatcher(Dispatchers.Default),
    )

  init {
    TestActivityDependencies.dispatcherProvider = dispatcherProvider
  }

  override fun starting(description: Description) {
    Log.d("TestLog", "register")
    with(dispatcherProvider) {
      IdlingRegistry.getInstance()
        .register(dispatcherIO, dispatcherDefault, dispatcherMain)
    }
  }

  override fun finished(description: Description) {
    with(dispatcherProvider) {
      IdlingRegistry.getInstance()
        .unregister(dispatcherIO, dispatcherDefault, dispatcherMain)
    }
  }
}
