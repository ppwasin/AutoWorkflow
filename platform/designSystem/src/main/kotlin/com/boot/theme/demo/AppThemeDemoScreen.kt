package com.boot.theme.demo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.boot.designSystem.R
import com.boot.theme.app.AppTheme

@Composable
fun DemoScreen(items: List<PhotographItem>) {
  LazyColumn(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize().background(AppTheme.colors.background)
  ) {
    item {
      TopAppBar(
        title = {
          Text(
            text = "The gallery",
            style = AppTheme.typography.h1,
            color = AppTheme.colors.primary
          )
        },
        backgroundColor = Color.Transparent,
        elevation = 2.dp
      )
    }
    items(items) { item -> GalleryItem(item) }
  }
}

@Composable
fun GalleryItem(item: PhotographItem) {
  Column(modifier = Modifier.padding(AppTheme.dimensions.paddingMedium)) {
    Text(
      text = item.description,
      style = AppTheme.typography.body,
      color = AppTheme.colors.textPrimary,
      modifier = Modifier.padding(AppTheme.dimensions.paddingSmall)
    )
    Image(
      painter =
        rememberImagePainter(data = item.photoUrl) { placeholder(R.drawable.ic_placeholder_24) },
      contentDescription = null,
      modifier = Modifier.size(256.dp)
    )
    Text(
      text = item.author,
      style = AppTheme.typography.caption,
      color = AppTheme.colors.textSecondary,
      modifier = Modifier.padding(AppTheme.dimensions.paddingSmall)
    )
  }
}

@Preview
@Composable
fun AppThemeDemoScreen() {
  val photographItems =
    listOf(
      PhotographItem(
        description = "Green water and a boat",
        photoUrl =
          "https://images.unsplash.com/photo-1596324121712-5bbc14482174?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=880&q=80",
        author = "Photograph by Tracey Isles"
      ),
      PhotographItem(
        description = "Rain drops on a flower",
        photoUrl =
          "https://images.unsplash.com/photo-1555662800-92f44b37a43d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=909&q=80",
        author = "Photograph by Andriyko Podilnyk"
      ),
      PhotographItem(
        description = "Green roof in front of the blue sky",
        photoUrl =
          "https://images.unsplash.com/photo-1512977851705-67ee4bf294f4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=860&q=80",
        author = "Photograph by Simone Hutsch"
      )
    )
  DemoScreen(photographItems)
}
