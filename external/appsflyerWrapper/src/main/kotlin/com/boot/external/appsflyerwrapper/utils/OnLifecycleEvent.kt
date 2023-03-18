package com.boot.external.appsflyerwrapper.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

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

@Composable
fun rememberLifecycleEvent(
	lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
	doOnStart: () -> Unit = {},
): Lifecycle.Event {
	var state by remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
	val currentDoOnStart = rememberUpdatedState(newValue = doOnStart)
	DisposableEffect(lifecycleOwner) {
		val observer = LifecycleEventObserver { _, event ->
			println("Lifecycler: $event")
			if (event == Lifecycle.Event.ON_START) currentDoOnStart.value()
			state = event
		}
		lifecycleOwner.lifecycle.addObserver(observer)
		onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
	}
	return state
}
