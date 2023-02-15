package com.boot.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchInputRow(text: String, setText: (String) -> Unit) {
  TextField(
    value = text,
    onValueChange = setText,
    modifier = Modifier.fillMaxWidth(),
    leadingIcon = {
      Icon(
        Icons.Default.Search,
        contentDescription = "",
        modifier = Modifier.padding(15.dp).size(24.dp),
      )
    },
    trailingIcon = {
      if (text.isNotEmpty()) {
        IconButton(
          onClick = {
            setText(
              "",
            ) // Remove text from TextField when you press the 'X' icon
          },
        ) {
          Icon(
            Icons.Default.Close,
            contentDescription = "",
            modifier = Modifier.padding(15.dp).size(24.dp),
          )
        }
      }
    },
    singleLine = true,
    shape = RectangleShape,
  )
}

@Preview(showBackground = true)
@Composable
fun SearchInputRowPreview() {
  SearchInputRow("sdfsdf") {}
}
