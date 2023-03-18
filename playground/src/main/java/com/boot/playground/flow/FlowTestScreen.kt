package com.boot.playground.flow

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun FlowTestScreen() {
	val scope = rememberCoroutineScope()
	val emitter = remember { Emitter(scope) }
	val collector1 = remember { Collector(scope) }
	val collector2 = remember { Collector(scope) }

	Column {
		Text(text = "Emitter", style = MaterialTheme.typography.titleLarge)
		Text(text = "Value: ${emitter.value}")
		Text(text = "tryEmitResult: ${emitter.tryEmitResult}")
		Button(onClick = emitter::dumpEvent) { Text("Dump") }

		Text(text = "Collector1", style = MaterialTheme.typography.titleLarge)
		Text(text = "Value: ${collector1.histories}")
		Button(onClick = { collector1.startObserve(emitter.relay) }) {
			Text("startObserve")
		}

		Text(text = "Collector2", style = MaterialTheme.typography.titleLarge)
		Text(text = "Value: ${collector2.histories}")
		Button(onClick = { collector2.startObserve(emitter.relay) }) {
			Text("startObserve")
		}
	}
}

class Collector(private val scope: CoroutineScope) {
	var histories by mutableStateOf("")
	fun startObserve(flow: Flow<Int>) {
		flow.onEach { histories += "$it, " }.flowOn(Dispatchers.IO).launchIn(scope)
	}
}

class Emitter(private val scope: CoroutineScope) {
	var value: Int by mutableStateOf(0)
	val relay =
		MutableSharedFlow<Int>(
			replay = 1,
			//    extraBufferCapacity = 1,
			//    onBufferOverflow = BufferOverflow.SUSPEND
		)
	var tryEmitResult: Boolean? by mutableStateOf(null)
	fun dumpEvent() {
		//    tryEmitResult = relay.tryEmit(++value)
		scope.launch { relay.emit(++value) }
	}
}
