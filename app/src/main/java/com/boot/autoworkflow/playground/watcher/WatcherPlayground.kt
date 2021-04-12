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
package com.boot.autoworkflow.playground.watcher

import com.boot.watchadoin.Stopwatch
import com.boot.watchadoin.saveAsHtml
import java.io.File
import kotlin.time.ExperimentalTime

fun expensiveOperation(stopwatch: Stopwatch) = stopwatch { Thread.sleep(125) }

fun moreExpensiveOperation(stopwatch: Stopwatch) = stopwatch { Thread.sleep(375) }

@ExperimentalTime
fun startWatchPlayground() {
  println("startWatchPlayground")
  val loopWatch = Stopwatch("🔁 loop")
  loopWatch {
    for (i in 0 until 4) {
      "⏭️ iteration $i".watch {
        expensiveOperation("🕰️".watch)
        moreExpensiveOperation("🕰 x3".watch)
      }
    }
  }

  println(loopWatch.toStringPretty())

  //  loopWatch.saveAsSvg(File("loopWatch.svg"))
  //
  loopWatch.saveAsHtml(File("loopWatch.html")) // interactive svg with pan and zoom
}
