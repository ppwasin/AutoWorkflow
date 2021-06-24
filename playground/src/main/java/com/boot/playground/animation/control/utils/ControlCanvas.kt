package com.boot.playground.animation.control.utils

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun ControlCanvas(onClick: () -> Unit, centerOffset: Offset) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
            .clickable(onClick = onClick)
    ) {
        drawCircle(
            color = Color.White,
            radius = 10f,
            center = centerOffset
        )
    }

}