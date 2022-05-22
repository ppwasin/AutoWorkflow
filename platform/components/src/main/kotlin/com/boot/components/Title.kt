package com.boot.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.boot.designsystem.theme.material.AppMaterialTheme

@Composable
fun Title(text: String) {
  Text(
    text = text,
    maxLines = 1,
    overflow = TextOverflow.Ellipsis,
    style = MaterialTheme.typography.headlineMedium,
    fontWeight = FontWeight.Bold
  )
}

@Preview(showSystemUi = true)
@Composable
fun TitlePreview() {
  AppMaterialTheme { Title("Title") }
}
