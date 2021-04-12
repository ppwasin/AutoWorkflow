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
package com.boot.autoworkflow

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.reflect.KFunction1
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

  @ExperimentalTime
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { MyApp() }
  }
}

@Composable
fun Greeting(textGenerator: KFunction1<(String) -> Unit, Unit>) {
  var text by remember { mutableStateOf("Initial") }
  LaunchedEffect(Unit) { textGenerator.invoke { text += "\n$it" } }
  Text(text)
}

@ExperimentalTime
@Composable
fun MyApp() {
  Surface(color = MaterialTheme.colors.background) {
    Column {
      Greeting(::testSubscribe)
      Divider(thickness = 2.dp)
      Greeting(::testSubscribe2)
      Divider(thickness = 2.dp)
      Greeting(::testRunBlock)
    }
  }
}

val fakeAPICall =
    Single.fromCallable {
      Thread.sleep(500)
      1
    }

val getCompletable: () -> Completable = {
  Completable.fromCallable { Thread.sleep(500) }.subscribeOn(Schedulers.io())
}

@SuppressLint("CheckResult")
fun testSubscribe(showText: (String) -> Unit) {

  fakeAPICall
      .flatMapCompletable {
        Completable.merge(
            listOf(getCompletable(), getCompletable(), getCompletable(), getCompletable()))
      }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribeOn(Schedulers.io())
      .subscribe { showText("[${Thread.currentThread().name}]On subscribe: this will print later") }

  showText("Last line of function: this will print first")
}

@SuppressLint("CheckResult")
fun testSubscribe2(showText: (String) -> Unit) {
  fakeAPICall
      .flatMapCompletable {
        Completable.merge(
            listOf(getCompletable(), getCompletable(), getCompletable(), getCompletable()))
      }
      .subscribeOn(Schedulers.io())
      .observeOn(Schedulers.computation())
      .subscribe { showText("[${Thread.currentThread().name}]On subscribe: this will print later") }
  showText("Last line of function: this will print first")
}

fun testRunBlock(showText: (String) -> Unit) {
  runBlocking {
    val job1 =
        launch {
          delay(1000)
          showText("Coroutine block#1")
        }
    val job2 =
        launch {
          delay(500)
          showText("Coroutine block#2")
        }
    joinAll(job1, job2)
    showText("Coroutine runblocking last line")
  }
}
