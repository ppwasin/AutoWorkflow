package com.boot.playground.animation

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class MySize(val width: Dp, val height: Dp)

@Composable
fun AnimationVectorPlayground(targetSize: MySize) {
  val animSize: MySize by
    animateValueAsState<MySize, AnimationVector2D>(
      targetSize,
      TwoWayConverter(
        convertToVector = { size: MySize ->
          // Extract a float value from each of the `Dp` fields.
          AnimationVector2D(size.width.value, size.height.value)
        },
        convertFromVector = { vector: AnimationVector2D ->
          MySize(vector.v1.dp, vector.v2.dp)
        },
      ),
    )
}
