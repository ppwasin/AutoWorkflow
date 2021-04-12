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
package com.boot.autoworkflow.dagger_playground

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Scope

object DependenciesInterfacePlayground {
  @Component(modules = [ThisModule::class]) @Scope1 interface ThisComponent : Dependencies

  interface Dependencies {
    fun getDepA(): DepA
  }
  @Scope @Retention(AnnotationRetention.RUNTIME) annotation class Scope1

  /** --------------------------- */
  @Module
  object ThisModule {
    @Scope1 @Provides fun provideDepA(): DepA = DepA()
    @Scope1 @Provides fun provideDepB(): DepB = DepB()
  }

  /** --------------------------- */
  class DepA
  class DepB

  /** --------------------------- */
  class Injectable(private val dependencies: Dependencies) {
    val a: DepA by lazy { dependencies.getDepA() }
  }

  class AtInject @Inject constructor(private val a: Lazy<DepA>)
}
