package com.boot.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@SuppressLint("UnrememberedMutableState")
@Composable
@Preview
fun TryDeriveState() {
  var a by remember { mutableStateOf(0) }
  var b by remember { mutableStateOf(0) }
  var isShowSum by remember {
    mutableStateOf(false) //
  }
  val sum = derivedStateOf {
    println("run sum")
    a + b
  }

  Column {
    Button(onClick = { a += 1 }) { Text("Increase") }
    Button(onClick = { isShowSum = !isShowSum }) { Text("toggle sum") }
    if (isShowSum) Text("sum: ${sum.value}")
  }
}
