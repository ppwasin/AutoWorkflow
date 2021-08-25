package com.boot.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchInputRow(text: String, setText: (String) -> Unit) {
  TextField(
    value = text,
    onValueChange = { value ->
      println("value: $value")
      setText(value)
    },
    modifier = Modifier.fillMaxWidth(),
    textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
    leadingIcon = {
      Icon(
        Icons.Default.Search,
        contentDescription = "",
        modifier = Modifier.padding(15.dp).size(24.dp)
      )
    },
    trailingIcon = {
      if (text.isNotEmpty()) {
        IconButton(
          onClick = {
            setText("") // Remove text from TextField when you press the 'X' icon
          }
        ) {
          Icon(
            Icons.Default.Close,
            contentDescription = "",
            modifier = Modifier.padding(15.dp).size(24.dp)
          )
        }
      }
    },
    singleLine = true,
    shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
    colors =
      TextFieldDefaults.textFieldColors(
        textColor = Color.White,
        cursorColor = Color.White,
        leadingIconColor = Color.White,
        trailingIconColor = Color.White,
        backgroundColor = MaterialTheme.colors.primary,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
      )
  )
}

@Preview(showBackground = true)
@Composable
fun SearchInputRowPreview() {
  SearchInputRow("") {}
}
