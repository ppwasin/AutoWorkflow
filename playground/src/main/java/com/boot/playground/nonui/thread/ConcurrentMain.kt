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

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun normalRun() {
  runBlocking {
    withContext(Dispatchers.Default) {
      massiveRun { CounterProvider.counter++ }
    }
    println("Counter = ${CounterProvider.counter}")
  }
}

fun volatile() {
  runBlocking {
    withContext(Dispatchers.Default) {
      massiveRun { CounterProvider.counterVolatile++ }
    }
    println("Counter = ${CounterProvider.counterVolatile}")
  }
}

fun atomic() = runBlocking {
  withContext(Dispatchers.Default) {
    massiveRun { CounterProvider.atomicCounter.incrementAndGet() }
  }
  println("Counter = ${CounterProvider.atomicCounter}")
}

fun factoryNormal() = runBlocking {
  withContext(Dispatchers.Default) {
    massiveRun { FactoryNormal.getOrCreate() }
  }
  println("NumberOfCounter = ${FactoryNormal.numberOfInstance}")
}

fun factoryAtomic() = runBlocking {
  withContext(Dispatchers.Default) {
    massiveRun { FactoryAtomic.getOrCreate() }
  }
  println("NumberOfCounter = ${FactoryAtomic.numberOfInstance}")
  val categoryLog = listOf<String>()
}

fun factorySynchronized() = runBlocking {
  withContext(Dispatchers.Default) {
    massiveRun { FactorySynchronized.getOrCreate() }
  }
  println("NumberOfCounter = ${FactorySynchronized.numberOfInstance}")
}

fun factoryGuardVolatile() = runBlocking {
  withContext(Dispatchers.Default) {
    massiveRun { FactoryGuardVolatile.getOrCreate() }
  }
  println("NumberOfCounter = ${FactoryGuardVolatile.numberOfInstance}")
}

fun factoryGeneric() = runBlocking {
  withContext(Dispatchers.Default) {
    massiveRun { FactoryGeneric.getOrCreate() }
  }
  println("NumberOfCounter = ${FactoryGeneric.numberOfInstance}")
}

fun daggerFactory() = runBlocking {
  withContext(Dispatchers.Default) {
    massiveRun { ConcurrentComponent.getOrCreate() }
  }
  println("NumberOfCounter = $daggerCounterInstanceCount")
}

fun main() {
  runSection("Normal Run", ::normalRun)
  runSection("Volatile Run", ::volatile)
  runSection("Atomic Run", ::atomic)
  runSection("FactoryNormal", ::factoryNormal)
  runSection("FactoryAtomic", ::factoryAtomic)
  runSection("FactorySynchronized", ::factorySynchronized)
  runSection("FactoryGuardVolatile", ::factoryGuardVolatile)
  runSection("FactoryGeneric", ::factoryGeneric)
  runSection("DaggerFactory", ::daggerFactory)
}

fun find(categoryLog: List<String>): String {
  var maxCategory = ""
  var maxCategoryCount = 0

  val map: MutableMap<String, Int> = mutableMapOf()
  for (category in categoryLog) {
    var categoryCount = map[category] ?: 0
    categoryCount += 1
    map[category] = categoryCount

    if (categoryCount > maxCategoryCount) {
      maxCategoryCount = categoryCount
      maxCategory = category
    }
  }

  return maxCategory
}
