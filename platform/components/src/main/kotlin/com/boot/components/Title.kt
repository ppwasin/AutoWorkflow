package com.boot.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.boot.theme.AppTheme

@Composable
fun Title(text: String) {
  Text(
    text = text,
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
    style = MaterialTheme.typography.h4,
    fontWeight = FontWeight.Bold
  )
}

@Preview(showSystemUi = true)
@Composable
fun TitlePreview() {
  AppTheme { Title("Title") }
}
