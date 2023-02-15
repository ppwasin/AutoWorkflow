package com.boot.components.gesture

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.pressGesture(
  scaleSize: Float = 1.2f,
  onClick: () -> Unit
): Modifier = composed {
  var selected by remember { mutableStateOf(false) }
  val scale by animateFloatAsState(if (!selected) scaleSize else 1f)
  graphicsLayer {
      scaleX = scale
      scaleY = scale
    }
    .pointerInput(Unit) {
      detectTapGestures(
        onPress = {
          selected = true
          if (tryAwaitRelease()) onClick()
          selected = false
        },
      )
    }
}
