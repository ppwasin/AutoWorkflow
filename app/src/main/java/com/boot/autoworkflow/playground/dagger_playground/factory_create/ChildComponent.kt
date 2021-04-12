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
package com.boot.autoworkflow.playground.dagger_playground.factory_create

import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [ParentComponent::class, ParentComponentBindInstance::class],
    modules = [ChildModule::class])
interface ChildComponent {
  fun inject(injectable: FactoryCreateInjectable)

  fun getDepA(): BindInstance
  fun getNormall(): ProvideNormally
  fun getParent(): ParentDep

  @Component.Factory
  interface Factory {

    fun create(
        parentComponent: ParentComponent,
        // Error: @BindsInstance parentComponentBindInstance: ParentComponentBindInstance,
        parentComponentBindInstance: ParentComponentBindInstance,
        @BindsInstance bindsInstance: BindInstance,
    ): ChildComponent
  }
}

@Module
object ChildModule {
  @Provides fun provideNormall(): ProvideNormally = ProvideNormally()
}
