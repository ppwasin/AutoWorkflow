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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.boot.autoworkflow.ui.theme.AppTheme
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          var text by remember { mutableStateOf("Initial") }
          LaunchedEffect(Unit) { testSubscribe { text += "\n$it" } }
          Greeting(text)
        }
      }
    }
  }
}

@Composable
fun Greeting(text: String) {
  Text(text)
}

// Start building your app here!
@Composable
fun MyApp() {
  Surface(color = MaterialTheme.colors.background) { Text(text = "Ready... Set... GO!") }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
  AppTheme { MyApp() }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
  AppTheme(darkTheme = true) { MyApp() }
}

fun testSubscribe(showText: (String) -> Unit) {
  val getCompletable: () -> Completable = {
    Completable.fromCallable { Thread.sleep(500) }.subscribeOn(Schedulers.io())
  }
  val api =
      Single.fromCallable {
        Thread.sleep(500)
        1
      }

  val dispoable =
      api
          .flatMapCompletable {
            Completable.merge(
                listOf(getCompletable(), getCompletable(), getCompletable(), getCompletable()))
          }
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe { showText("On subscribe: this will print later") }

  showText("Last line of function: this will print first")
}
