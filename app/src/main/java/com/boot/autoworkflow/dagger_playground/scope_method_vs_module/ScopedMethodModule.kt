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
package com.boot.autoworkflow.dagger_playground.scope_method_vs_module

import dagger.Binds
import dagger.Module
import javax.inject.Inject

@Module
interface ScopedMethodModule {
  @Scope1 @Binds fun getScopeDep(impl: ScopedDependencyImpl): ScopedDependency

  @Binds fun getUnScopeDep(impl: UnScopedDependencyImpl): UnScopedDependency
}

interface ScopedDependency

class ScopedDependencyImpl @Inject constructor() : ScopedDependency

interface UnScopedDependency

class UnScopedDependencyImpl @Inject constructor() : UnScopedDependency
