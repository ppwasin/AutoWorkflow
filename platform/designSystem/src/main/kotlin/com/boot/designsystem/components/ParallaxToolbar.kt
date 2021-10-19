package com.boot.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.boot.designsystem.theme.app.AppTheme

@Composable
fun <T> ParallaxToolbar(data: T) {
  TopAppBar(
    contentPadding = PaddingValues(),
    backgroundColor = Color.White,
    modifier = Modifier.height(AppTheme.dimensions.AppBarCollapsedHeight)
  ) {
    //
  }
}
