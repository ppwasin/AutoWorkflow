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
package com.boot.autoworkflow.dagger_playground.lazy_not_all

import dagger.Component
import dagger.Lazy
import dagger.Module
import dagger.Provides
import javax.inject.Inject

object LazyNotAllPlayground {
  @Component(modules = [ThisModule::class])
  interface ThisComponent {
    fun inject(injectable: Injectable)
  }

  /** --------------------------- */
  @Module
  object ThisModule {
    @Provides fun getA(): A = A()
    @Provides fun getB(): B = B()
  }

  /** --------------------------- */
  class A
  class B
  class C
  /** --------------------------- */
  class Injectable {
    @Inject lateinit var a: A

    @Inject lateinit var b: Lazy<B>

    // @Inject lateinit var c: Lazy<C> //Error
  }
}
