package com.boot.playground.test.coroutineidle

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource
import com.boot.playground.test.TestLogger
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Delay
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.Runnable
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume

@OptIn(InternalCoroutinesApi::class)
class IdlingResourceDispatcher(
	private val actualDispatcher: CoroutineDispatcher,
	private val isDelayEnable: Boolean = false,
) : CoroutineDispatcher(), IdlingResource, Delay {

	private val counter =
		CountingIdlingResource(actualDispatcher::class.simpleName)

	override fun dispatch(context: CoroutineContext, block: Runnable) =
		actualDispatcher.dispatch(
			context,
			Runnable {
				counter.increment()
				block.run()
				counter.decrement()
			},
		)

	override fun getName(): String = counter.name

	override fun isIdleNow(): Boolean =
		counter.isIdleNow.also {
			TestLogger.log("IdlingResource:isIdleNow for $name = $it")
		}

	override fun registerIdleTransitionCallback(
		callback: IdlingResource.ResourceCallback
	) = counter.registerIdleTransitionCallback(callback)

	override fun scheduleResumeAfterDelay(
		timeMillis: Long,
		continuation: CancellableContinuation<Unit>
	) {
		if (isDelayEnable) Thread.sleep(timeMillis)
		continuation.resume(Unit)
	}
}

class MyContext : CoroutineContext.Element {
	companion object Key : CoroutineContext.Key<MyContext>

	override val key: CoroutineContext.Key<MyContext> = Key
}

val myInterceptor =
	object : ContinuationInterceptor {
		override val key: CoroutineContext.Key<MyContext> = MyContext

		override fun <T> interceptContinuation(
			continuation: Continuation<T>
		): Continuation<T> {
			val name = continuation.context[CoroutineName]?.name
			val job = continuation.context[Job]
			TestLogger.log("Intercept setup: $name, $job")

			//      return Continuation(EmptyCoroutineContext) {
			//        thread(name = "myThread") { continuation.resumeWith(it) }
			//      }

			return Continuation(continuation.context) {
				TestLogger.log("Intercept for $name, $job: before")
				continuation.resumeWith(it)
				TestLogger.log("Intercept for $name, $job: after resumeWith")
			}
		}
	}
