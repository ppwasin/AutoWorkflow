package com.boot.components.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.boot.theme.AppTheme

@Composable
fun SearchScreenSlot(
  onSubmit: (String) -> Unit,
  onBack: () -> Unit,
  content: LazyListScope.(String) -> Unit
) {
  val searchTextState = remember { mutableStateOf(TextFieldValue("")) }
  LazyColumn {
    item { SearchInputRow(searchTextState) }
    content(searchTextState.value.text)
  }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
  AppTheme {
    SearchScreenSlot(
      onSubmit = {},
      onBack = {},
      content = {
        item { Text("1") }
        item { Text("2") }
      }
    )
  }
}
