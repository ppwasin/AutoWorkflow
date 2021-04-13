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
package com.boot.playground.nonui.dagger_playground

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Component(modules = [ParentScopeModule::class])
interface ParentSCopeComponent : ParentScopeDependencies {
  fun inject(injectable: InjectableParent)
}

@Module
object ParentScopeModule {

  @Provides @Singleton fun provideA(): A = A()
}

interface ParentScopeDependencies {
  fun test(): A
}

@Singleton
@Component(dependencies = [ParentScopeDependencies::class])
interface ChildScopeComponent {
  fun inject(injectable: InjectableChild)
}

class A {
  private var count = 0
  init {
    println("[A] init")
  }

  fun run() {
    println("[A] run $count")
    count += 1
  }
}

class InjectableParent {
  @Inject lateinit var a: A
}

class InjectableChild {
  @Inject lateinit var a: A
}

fun main() {
  val injectableChild = InjectableChild()
  val injectableParent = InjectableParent()
  val parent = DaggerParentSCopeComponent.builder().build()
  val child = DaggerChildScopeComponent.builder().parentScopeDependencies(parent).build()

  println("Before inject parent")
  parent.inject(injectableParent)

  println("Before inject child")
  child.inject(injectableChild)

  println("Before run")
  injectableChild.a.run()
  injectableParent.a.run()
}
