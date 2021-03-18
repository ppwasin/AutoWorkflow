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
package com.boot.autoworkflow.dagger_playground.abstract_vs_object

import com.boot.autoworkflow.dagger_playground.abstract_vs_object.dep.AbstractObjectModel
import com.boot.autoworkflow.dagger_playground.abstract_vs_object.dep.DummyCall
import com.boot.autoworkflow.dagger_playground.abstract_vs_object.dep.SecondAbstractModel
import com.boot.autoworkflow.dagger_playground.abstract_vs_object.dep.SecondObjectModel
import javax.inject.Inject

class FirstInjectable {
  @Inject lateinit var dummy: DummyCall
  init {
    DaggerAppComponent.builder().build().inject(this)
  }
}

class SecondInjectable {
  @Inject lateinit var dummyCall: DummyCall
  @Inject lateinit var secondObjectModel: SecondObjectModel
  @Inject lateinit var abstractObjectModel: AbstractObjectModel
  @Inject lateinit var secondAbstractModel: SecondAbstractModel
}
