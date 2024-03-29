/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.boot.playground.nonui.thread

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

suspend fun massiveRun(action: suspend () -> Unit) {
	val n = 100 // number of coroutines to launch
	val k = 1000 // times an action is repeated by each coroutine
	val time = measureTimeMillis {
		coroutineScope { // scope for coroutines
			repeat(n) { launch { repeat(k) { action() } } }
		}
	}
	println("Completed ${n * k} actions in $time ms")
}
