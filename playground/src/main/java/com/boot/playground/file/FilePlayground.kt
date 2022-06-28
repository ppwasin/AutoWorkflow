package com.boot.playground.file

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
@Suppress("unused")
fun FilePlayground() {
  val context = LocalContext.current
  val fileStateList by remember {
    mutableStateOf(
      listOf(
        FileViewState(
          title = "System Path (Preinstall)",
          FileInfo(fullPath = "/data/etc/appchannel", fileName = "test.txt")
        ),
        FileViewState(
          title = "Access via Lib",
          FileInfo(fullPath = "${context.filesDir}/data/etc/appchannel", fileName = "test.txt")
        ),
        FileViewState(
          title = "Explicitly define path",
          FileInfo(
            fullPath = "/data/data/com.boot.playground/data/etc/appchannel",
            fileName = "test.txt"
          )
        )
      )
    )
  }

  Surface {
    LazyColumn(Modifier.background(MaterialTheme.colorScheme.background)) {
      itemsIndexed(items = fileStateList, key = { _, item -> item.title }) {
        index: Int,
        item: FileViewState ->
        FileRunner(title = item.title, fileInfo = item.fileInfo)

        if (fileStateList.lastIndex != index) Divider(Modifier.padding(vertical = 12.dp))
      }
    }
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
fun FileRunner(title: String, fileInfo: FileInfo) {
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
