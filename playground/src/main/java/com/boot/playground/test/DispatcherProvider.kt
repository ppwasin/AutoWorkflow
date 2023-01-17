package com.boot.playground.test

import kotlinx.coroutines.CoroutineDispatcher

class DispatcherProvider<out T : CoroutineDispatcher>(
  val dispatcherIO: T,
  val dispatcherDefault: T,
  val dispatcherMain: T,
)
