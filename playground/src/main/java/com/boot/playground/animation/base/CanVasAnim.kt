package com.boot.playground.animation.base

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion

@Composable
fun CanvasWrapper(radius: Float, alpha: Float) {
    Canvas(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(Color.Black)) {
        drawCircle(color = Color.White, radius = radius, alpha = alpha)
    }
}