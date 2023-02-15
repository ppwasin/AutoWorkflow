package com.boot.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.boot.designsystem.theme.material.AppMaterialTheme

class DataItem(val key: String, val value: String)

class UiState(
  val title: String,
  val subtitle: String,
  val items: List<DataItem>
)

@Composable
fun ListTest(viewModel: ExampleViewModel = remember { ExampleViewModel() }) {
  LaunchedEffect(Unit) { viewModel.fetchData() }
  val viewState = viewModel.uiState ?: return

  Column(
    Modifier.padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Text(viewState.title, fontSize = 24.sp)
    Text(viewState.subtitle)

    LazyColumn {
      items(viewState.items) { content ->
        Row(
          modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
        ) {
          Text(content.key)
          Text(content.value)
        }
        Divider()
      }
    }
  }
}

class ExampleViewModel : ViewModel() {

  var uiState by mutableStateOf<UiState?>(null)
    private set

  // Business logic
  suspend fun fetchData() {
    uiState =
      UiState(
        title = "Title",
        subtitle = "Subtitle",
        items =
          listOf(
            DataItem("key1", "value1"),
            DataItem("key2", "value2"),
            DataItem("key3", "value3"),
            DataItem("key4", "value4"),
            DataItem("key5", "value5"),
            DataItem("key6", "value6"),
          ),
      )
  }
}

@Preview
@Composable
fun ListPreview() {
  AppMaterialTheme { ListTest() }
}
