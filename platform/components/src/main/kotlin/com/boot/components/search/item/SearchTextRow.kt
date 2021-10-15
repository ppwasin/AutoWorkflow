package com.boot.components.search.item

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.boot.components.ComponentColors
import com.boot.theme.material.AppMaterialTheme

@Composable
fun SearchTextRow(title: String, subTitle: String? = null) {
  Column {
    Text(
      title,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis,
      textAlign = TextAlign.Start,
    )
    if (!subTitle.isNullOrEmpty()) {
      Text(
        subTitle,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.subtitle1,
        color = ComponentColors.subTitleText
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun SearchTextRowPreview() {
  AppMaterialTheme { SearchTextRow(title = "title", subTitle = "subtitle") }
}
