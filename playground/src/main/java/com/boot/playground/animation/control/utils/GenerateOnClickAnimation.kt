package com.boot.playground.animation.control.utils

import androidx.compose.animation.core.Animation
import androidx.compose.animation.core.AnimationVector
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.geometry.Offset
import com.boot.playground.animation.control.utils.AnimState.PAUSED
import com.boot.playground.animation.control.utils.AnimState.RUNNING
import kotlinx.coroutines.launch

class AnimationClick(val onClick: () -> Unit, val animationValue: Float) {
  val offset = Offset(x = animationValue, y = animationValue)
}

@Composable
fun <V : AnimationVector> generateOnClickAnimation(
  animation: Animation<Float, V>
): AnimationClick {

  // We will manually handle the play time of the animation
  var playTime by remember { mutableStateOf(0L) }

  // Do not use LaunchedEffect for the callback events such as when user clicks
  // to start the animation again. Instead use rememberCoroutineScope.
  val animationScope = rememberCoroutineScope()

  var animationState by remember { mutableStateOf(PAUSED) }
  var animationValue by remember { mutableStateOf(0f) }

  val onClick: () -> Unit = {
    // Toggle animation state
    animationState =
      when (animationState) {
        RUNNING -> PAUSED
        PAUSED -> RUNNING
      }

    animationScope.launch {
      // Need to extract the already played time
      // If user pauses and resumes the animation, shifting start time by
      // play time will make sure that the animation will continue from the
      // last played time
      var startTime = withFrameNanos { it } - playTime

      // Only continue animating if the state is running
      while (animationState == RUNNING) {
        if (animation.isFinishedFromNanos(playTime))
          startTime = withFrameNanos { it }
        playTime = withFrameNanos { it } - startTime
        animationValue = animation.getValueFromNanos(playTime)
      }
    }
  }

  return AnimationClick(onClick = onClick, animationValue = animationValue)
}
