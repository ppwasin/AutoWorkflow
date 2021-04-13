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

import android.annotation.SuppressLint
import androidx.annotation.GuardedBy
import com.boot.playground.nonui.thread.FactoryGeneric.Counter
import dagger.BindsInstance
import dagger.Component
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicReference

object CounterProvider {
  var counter = 0
  @Volatile // in Kotlin `volatile` is an annotation
  var counterVolatile = 0
  val atomicCounter = AtomicInteger()
}

object FactoryNormal {
  val numberOfInstance = AtomicInteger()
  class Counter {
    init {
      numberOfInstance.incrementAndGet()
    }
  }
  private var counter: Counter? = null
  fun getOrCreate(): Counter = counter ?: Counter().also { counter = it }
}

object FactoryAtomic {
  val numberOfInstance = AtomicInteger()
  class Counter {
    init {
      numberOfInstance.incrementAndGet()
    }
  }
  private var counter: AtomicReference<Counter> = AtomicReference()
  @SuppressLint("NewApi") fun getOrCreate(): Counter = counter.updateAndGet { Counter() }
}

object FactorySynchronized {
  val numberOfInstance = AtomicInteger()
  class Counter {
    init {
      numberOfInstance.incrementAndGet()
    }
  }
  private var counter: Counter? = null
  private val lock = Any()
  fun getOrCreate(): Counter =
      counter ?: synchronized(lock) { counter ?: Counter().also { counter = it } }
}

object FactoryGuardVolatile {
  val numberOfInstance = AtomicInteger()
  class Counter {
    init {
      numberOfInstance.incrementAndGet()
    }
  }
  @Volatile @GuardedBy("lock") private var counter: Counter? = null
  private val lock = Any()
  fun getOrCreate(): Counter {
    if (counter == null) {
      synchronized(lock) {
        if (counter == null) {
          counter = Counter()
        }
      }
    }
    return counter!!
  }
}

object FactoryGeneric : GetOrCreateFactory<Counter> by GetOrCreateFactory(create = { Counter() }) {
  val numberOfInstance = AtomicInteger()
  class Counter {
    init {
      numberOfInstance.incrementAndGet()
    }
  }
}

abstract class InstanceProviderBase<T> {
  private var instance: T? = null
  private val lock = Any()
  abstract fun create(): T
  fun getOrCreate(): T =
      instance ?: synchronized(lock) { instance ?: create().also { instance = it } }
}

interface GetOrCreateFactory<T> {
  fun getOrCreate(): T
  companion object {
    operator fun <T> invoke(create: () -> T): GetOrCreateFactory<T> {
      return object : GetOrCreateFactory<T> {
        val lock = Any()
        private var instance: T? = null
        override fun getOrCreate(): T {
          return instance ?: synchronized(lock) { instance ?: create().also { instance = it } }
        }
      }
    }
  }
}

// V2=========================================

interface InstanceProvider<T> {
  fun getOrCreate(create: () -> T): T
  companion object {
    operator fun <T> invoke(): InstanceProvider<T> {
      return object : InstanceProvider<T> {
        val lock = Any()
        private var instance: T? = null
        override fun getOrCreate(create: () -> T): T {
          return instance ?: synchronized(lock) { instance ?: create().also { instance = it } }
        }
      }
    }
  }
}

// Dagger=========================================

val daggerCounterInstanceCount = AtomicInteger()

@Component
interface ConcurrentComponent {
  fun someString(): String

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance str: String): ConcurrentComponent
  }

  companion object : InstanceProviderBase<ConcurrentComponent>() {
    override fun create(): ConcurrentComponent {
      daggerCounterInstanceCount.incrementAndGet()
      return DaggerConcurrentComponent.factory().create("Test")
    }
  }
}
