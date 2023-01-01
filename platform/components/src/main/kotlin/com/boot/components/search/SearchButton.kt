package com.boot.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boot.components.ComponentColors

@Composable
fun SearchButton(onClick: () -> Unit) {
  Row(
    modifier =
      Modifier.fillMaxWidth()
        .clickable { onClick() }
        .background(
          color = ComponentColors.searchInputBackground,
          shape = RoundedCornerShape(10.dp)
        )
        .shadow(1.dp)
        .padding(8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      Icons.Filled.Search,
      contentDescription = null,
      modifier = Modifier.size(ButtonDefaults.IconSize)
    )
    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
    Text("Search ...")
  }
}

@Preview(showBackground = true)
@Composable
fun SearchButtonPreview() {
  Column { SearchButton {} }
}
