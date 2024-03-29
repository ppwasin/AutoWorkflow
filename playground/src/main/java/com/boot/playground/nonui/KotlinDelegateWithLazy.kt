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
package com.boot.playground.nonui

import com.boot.playground.nonui.KotlinLazyPlayground.Factory
import com.boot.playground.nonui.KotlinLazyPlayground.Test

/** ------------- */
fun main() {
	KotlinLazyPlayground.run {
		val test = Test(Factory())
		println("[main] Before Run A")
		test.runA()
	}
}

/** ------------- */
object KotlinLazyPlayground {
	class AImpl : A {
		init {
			println("[AImpl] init")
		}

		override fun runA() {
			println("[AImpl] runA")
		}
	}

	interface A {
		fun runA()
	}

	class Factory {
		//    val aLazy by lazy { AImpl() }
		fun functionLazy() = AImpl()
	}

	/** ------------- */
	class Test(factory: Factory) : A by factory.functionLazy()
}
