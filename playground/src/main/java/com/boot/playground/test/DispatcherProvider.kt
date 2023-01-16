package com.boot.playground.test

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatcherProvider<out T: CoroutineDispatcher>(
  val dispatcherIO: T,
  val dispatcherDefault: T,
  val dispatcherMain: T,
)
