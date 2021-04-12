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
package com.boot.autoworkflow.playground.dagger_playground.scope_method_vs_module

import dagger.Component

@Scope1
@Component(modules = [ScopedMethodModule::class, ScopedInterfaceModule::class])
interface ScopePlaygroundComponent {
  fun inject(point1: Point1)
  fun inject(point1: Point2)
}