package com.boot.components.search

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SearchScreen(onSubmit: (String) -> Unit, onBack: () -> Unit) {
  //  LazyColumn { item { SearchInputRow()} }
  Text("SearchScreen")
}
