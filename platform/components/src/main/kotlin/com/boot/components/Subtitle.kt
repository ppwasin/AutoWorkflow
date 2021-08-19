package com.boot.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.boot.theme.AppTheme

@Composable
fun SubTitle(text: String) {
  Text(
    text = text,
    overflow = TextOverflow.Ellipsis,
    style = MaterialTheme.typography.h5,
    color = Color.Gray,
    maxLines = 2,
  )
}

@Preview(showSystemUi = true)
@Composable
fun SubTitlePreview() {
  AppTheme { SubTitle(getRandomString(100)) }
}

private fun getRandomString(length: Int): String {
  val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
  return (1..length).map { allowedChars.random() }.joinToString("") + "##END##"
}
