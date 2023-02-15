package com.boot.playground.animation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun AnimAsStatePlayground(enabled: Boolean) {
  // Whenever the target value changes, new animation
  // will start to the new target value
  val alpha: Float by
    animateFloatAsState(
      targetValue = if (enabled) 1f else 0.2f,
      animationSpec = tween(durationMillis = 3000),
    )
  Box(Modifier.fillMaxSize().graphicsLayer(alpha = alpha).background(Color.Red))
}
