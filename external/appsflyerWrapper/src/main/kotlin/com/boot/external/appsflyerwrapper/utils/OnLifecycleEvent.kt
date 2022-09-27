package com.boot.external.appsflyerwrapper.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun rememberLifecycleEvent(
  lifecycle: Lifecycle,
  onEvent: (Lifecycle.Event) -> Unit
) {
  val onEventCurrent = rememberUpdatedState(newValue = onEvent)
  DisposableEffect(lifecycle) {
    val observer = LifecycleEventObserver { _, event ->
      onEventCurrent.value(event)
    }
    lifecycle.addObserver(observer)
    onDispose { lifecycle.removeObserver(observer) }
  }
}
