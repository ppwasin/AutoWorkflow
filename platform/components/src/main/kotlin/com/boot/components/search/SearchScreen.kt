package com.boot.components.search

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.boot.components.search.action.SearchAction
import com.boot.components.search.action.SearchAction.Idle
import com.boot.components.search.action.SearchAction.Result.Content
import com.boot.components.search.action.SearchAction.Result.Error
import com.boot.components.search.action.SearchAction.Searching
import com.boot.theme.AppTheme

@Composable
fun SearchScreenSlot(onSubmit: (String) -> Unit, onBack: () -> Unit) {
  val searchTextState = remember { mutableStateOf(TextFieldValue("")) }
  val (searchAction, setSearchAction) = remember { mutableStateOf<SearchAction>(Idle) }
  LazyColumn {
    item { SearchInputRow(searchTextState) }

    when (searchAction) {
      Idle -> item { Text("TODO: Idle") }
      is Content<*> ->
        items(searchAction.content) {
          if (searchAction.content.isEmpty())
            Text("Cannot find anything for ${searchAction.keyword}")
          else {
            Text("TODO: Content")
          }
        }
      is Error -> item { Text("Error please try again (${searchAction.errorMsg})") }
      is Searching -> item { Text("Searching for ${searchAction.keyword}") }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
  AppTheme {
    SearchScreenSlot(
      onSubmit = {},
      onBack = {},
    )
  }
}
