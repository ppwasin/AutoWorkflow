package com.boot.playground.animation.control

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
@Preview
fun GesturePlayground() {
  val offsetAnim = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }
  Box(
      modifier =
          Modifier.fillMaxSize().background(Color.White).pointerInput(Unit) {
            coroutineScope {
              while (true) {
                // Detect a tap event and obtain its position.
                val position = awaitPointerEventScope { awaitFirstDown().position }
                launch {
                  // Animate to the tap position.
                  offsetAnim.animateTo(position)
                }
              }
            }
          }) {
    Text("Offset: X: ${offsetAnim.value.x}, Y: ${offsetAnim.value.y}")
    Circle(offsetAnim.value)
  }
}

@Composable
fun Circle(offset: Offset) {
  Canvas(
      modifier = Modifier.fillMaxSize(),
  ) { drawCircle(color = Color.Red, radius = 40f, center = offset) }
}

private fun Offset.toIntOffset() = IntOffset(x.roundToInt(), y.roundToInt())
