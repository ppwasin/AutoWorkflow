package com.boot.playground.test

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

object DispatcherProvider {
  val dispatcherIO: CoroutineDispatcher = Dispatchers.IO
  val dispatcherDefault: CoroutineDispatcher = Dispatchers.Default
  var dispatcherMain: CoroutineDispatcher = Dispatchers.Main
}
