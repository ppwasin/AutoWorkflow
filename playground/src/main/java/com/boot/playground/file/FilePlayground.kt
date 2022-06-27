package com.boot.playground.file

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
@Suppress("unused")
fun FilePlayground() {
  val context = LocalContext.current
  Column {
    FileRunner(
      title = "System Path (Preinstall)",
      FileTest(fullPath = "/data/etc/appchannel", fileName = "test.txt")
    )

    Divider(Modifier.padding(vertical = 12.dp))

    FileRunner(
      title = "Access via Lib",
      FileTest(fullPath = "${context.filesDir}/data/etc/appchannel", fileName = "test.txt")
    )

    Divider(Modifier.padding(vertical = 12.dp))

    FileRunner(
      title = "Explicitly define path",
      FileTest(
        fullPath = "/data/data/com.boot.playground/data/etc/appchannel",
        fileName = "test.txt"
      )
    )
  }
}

@Composable
fun RunResult(block: @Composable () -> Unit) {
  Column(
    modifier =
      Modifier.fillMaxWidth()
        .padding(vertical = 8.dp)
        .background(Color.LightGray.copy(alpha = 0.3f)),
    verticalArrangement = Arrangement.SpaceBetween
  ) { block() }
}

@Composable
fun FileRunner(title: String, fileInfo: FileTest) {
  var writeResult by remember { mutableStateOf("") }
  var readResult by remember { mutableStateOf("") }
  Column(Modifier.padding(12.dp)) {
    Text(title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
    Text("path: ${fileInfo.fullPath}")
    Button(
      onClick = {
        fileInfo.apply {
          writeResult = writeFile("123456")
          readResult = readFile() ?: ""
        }
      }
    ) { Text("Run") }
    RunResult {
      Text("Write Result:")
      Text(writeResult)
    }

    RunResult {
      Text("Read Result:")
      Text(readResult)
    }
  }
}
