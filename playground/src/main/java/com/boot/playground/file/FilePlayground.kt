package com.boot.playground.file

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
@Suppress("unused")
fun FilePlayground() {
  val context = LocalContext.current
  LaunchedEffect(key1 = 1) {
    println("File1=========================")
    FileTest(fullPath = "/data/etc/appchannel", fileName = "test.txt").apply {
      writeFile("123456")
      println("Read: ${readFile()}")
    }

    println("File2============================")
    FileTest(fullPath = "${context.filesDir}/data/etc/appchannel", fileName = "test.txt").apply {
      writeFile("123456")
      println("Read: ${readFile()}")
    }

    println("File3============================")
    FileTest(fullPath = null, fileName = "no_speicify.txt").apply {
      writeFile("123456")
      println("Read: ${readFile()}")
    }
  }
}
