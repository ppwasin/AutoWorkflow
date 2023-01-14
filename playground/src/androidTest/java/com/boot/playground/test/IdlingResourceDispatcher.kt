package com.boot.playground.test

import android.util.Log
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Runnable

class IdlingResourceDispatcher(
  private val actualDispatcher: CoroutineDispatcher
) : CoroutineDispatcher(), IdlingResource {

  private val countingIdlingResource =
    CountingIdlingResource(actualDispatcher::class.simpleName)
  override fun dispatch(context: CoroutineContext, block: Runnable) {
    Log.d(
      "TestLog",
      "IdlingResource:dispatch start for $name ($actualDispatcher)"
    )
    countingIdlingResource.increment()
    actualDispatcher.dispatch(
      context,
      Runnable {
        try {
          block.run()
        } finally {
          countingIdlingResource.decrement()
          Log.d(
            "TestLog",
            "IdlingResource:dispatch stop for $name ($actualDispatcher), ${countingIdlingResource.isIdleNow}",
          )
        }
      },
    )
  }

  override fun getName(): String = countingIdlingResource.name

  override fun isIdleNow(): Boolean =
    countingIdlingResource.isIdleNow.also {
      Log.d("TestLog", "IdlingResource:isIdleNow for $name = $it")
    }

  override fun registerIdleTransitionCallback(
    callback: IdlingResource.ResourceCallback
  ) = countingIdlingResource.registerIdleTransitionCallback(callback)
}

class MyContext : CoroutineContext.Element {
  companion object Key : CoroutineContext.Key<MyContext>
  override val key: CoroutineContext.Key<MyContext> = Key
}

val myInterceptor =
  object : ContinuationInterceptor {
    override val key: CoroutineContext.Key<MyContext> = MyContext.Key

    override fun <T> interceptContinuation(
      continuation: Continuation<T>
    ): Continuation<T> {
      println(
        "interceptContinuation: ${continuation.context[CoroutineName]?.name}"
      )
      return Continuation(EmptyCoroutineContext) {
        thread(name = "myThread") { continuation.resumeWith(it) }
      }
    }

    override fun toString() = "My Interceptor"
  }
