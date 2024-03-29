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

import com.boot.playground.nonui.DaggerWithKotlinGet.Injectable
import dagger.Component
import dagger.Lazy
import dagger.Module
import dagger.Provides
import javax.inject.Inject

fun main() {
	DaggerWithKotlinGet.run {
		val test = Injectable()
		DaggerDaggerWithKotlinGet_ThisComponent.builder().build().inject(test)

		println("[main] Before Call A")
		test.a

		println("[main] Before Run A")
		test.a.run()
	}
}

object DaggerWithKotlinGet {
	@Component(modules = [ThisModule::class])
	interface ThisComponent {
		fun inject(injectable: Injectable)
	}

	/** --------------------------- */
	@Module
	object ThisModule {
		@Provides
		fun getA(): A = A()
	}

	/** --------------------------- */
	class A {
		init {
			println("[A] init ${hashCode()}")
		}

		fun run() {
			println("[A] runA ${hashCode()}")
		}
	}

	/** --------------------------- */
	class Injectable {
		@Inject
		lateinit var aLazy: Lazy<A>
		val a: A
			get() = aLazy.get()
	}
}
