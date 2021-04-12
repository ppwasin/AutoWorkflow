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
package com.boot.watchadoin

import kotlin.time.ExperimentalTime

/**
 * Support for Trace Event Format
 *
 * https://docs.google.com/document/d/1CvAClvFfyA5R-PhYUmn5OOQtYMH4h6I0nSsKchNAySU/edit
 *
 * WANRING: Looks super bad if you try using it with one thread and multiple coroutines running in
 * parallel.
 *
 * USAGE: open chrome and type `chrome://tracing` in the address bar
 */
data class TraceEventsReport(val traceEvents: List<TraceEvent>, val displayTimeUnit: String = "ns")

data class TraceEvent(
    val cat: String? = null,
    val pid: Int,
    val tid: Long,
    val id: Long? = null,
    val ts: Long,
    val dur: Long? = null,
    val ph: String,
    val name: String,
    val cname: String? = null
)

fun Stopwatch.asTraceEventsReport(): TraceEventsReport {
  val traces = traceEventList().sortedBy { it.ts }
  return TraceEventsReport(traceEvents = traces)
}

@OptIn(ExperimentalTime::class)
private fun Stopwatch.traceEventList(relativeStartTime: Long = 0): List<TraceEvent> =
    timelines().map {
      TraceEvent(
          name = it.name,
          cat = "watchadoin",
          ph = "X",
          ts = it.relativeStart.inMicroseconds.toLong(),
          dur = it.duration.inMicroseconds.toLong(),
          pid = 1,
          tid = it.tid)
    }
