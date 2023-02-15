package com.boot.playground.async

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun AsyncPlayground() {
  Column {
    Greeting(::testSubscribe)
    Divider(thickness = 2.dp)
    Greeting(::testSubscribe2)
    Divider(thickness = 2.dp)
    Greeting(::testRunBlock)
  }
}

@Composable
private fun Greeting(textGenerator: KFunction1<(String) -> Unit, Unit>) {
  var text by remember { mutableStateOf("Initial") }
  LaunchedEffect(Unit) { textGenerator.invoke { text += "\n$it" } }
  Text(text)
}

private val fakeAPICall =
  Single.fromCallable {
    Thread.sleep(500)
    1
  }

private val getCompletable: () -> Completable = {
  Completable.fromCallable { Thread.sleep(500) }.subscribeOn(Schedulers.io())
}

@SuppressLint("CheckResult")
private fun testSubscribe(showText: (String) -> Unit) {

  fakeAPICall
    .flatMapCompletable {
      Completable.merge(
        listOf(
          getCompletable(),
          getCompletable(),
          getCompletable(),
          getCompletable(),
        ),
      )
    }
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeOn(Schedulers.io())
    .subscribe {
      showText(
        "[${Thread.currentThread().name}]On subscribe: this will print later",
      )
    }

  showText("Last line of function: this will print first")
}

@SuppressLint("CheckResult")
private fun testSubscribe2(showText: (String) -> Unit) {
  fakeAPICall
    .flatMapCompletable {
      Completable.merge(
        listOf(
          getCompletable(),
          getCompletable(),
          getCompletable(),
          getCompletable(),
        ),
      )
    }
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.computation())
    .subscribe {
      showText(
        "[${Thread.currentThread().name}]On subscribe: this will print later",
      )
    }
  showText("Last line of function: this will print first")
}

private fun testRunBlock(showText: (String) -> Unit) {
  runBlocking {
    val job1 = launch {
      delay(1000)
      showText("Coroutine block#1")
    }
    val job2 = launch {
      delay(500)
      showText("Coroutine block#2")
    }
    joinAll(job1, job2)
    showText("Coroutine runblocking last line")
  }
}
