package com.boot.playground.animation.control

import androidx.compose.animation.core.DecayAnimation
import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Composable
fun DecayAnimationPlayground() {
  val decayAnimation = remember {
    DecayAnimation(
        animationSpec =
            FloatExponentialDecaySpec(
                // How quick the animation will stop
                frictionMultiplier = 0.7f),
        initialValue = 0f,
        initialVelocity = 600f)
  }

  // We will manually handle the play time of the animation
  var playTime by remember { mutableStateOf(0L) }

  val animationScope = rememberCoroutineScope()
  var animationState by remember { mutableStateOf(AnimState.PAUSED) }
  var animationValue by remember { mutableStateOf(0f) }

  val onClick: () -> Unit = {
    // Toggle animation state
    animationState =
        when (animationState) {
          AnimState.RUNNING -> AnimState.PAUSED
          AnimState.PAUSED -> AnimState.RUNNING
        }

    animationScope.launch {
      // Need to extract the already played time
      // If user pauses and resumes the animation, shifting start time by
      // play time will make sure that the animation will continue from the
      // last played time
      var startTime = withFrameNanos { it } - playTime

      // Only continue animating if the state is running
      while (animationState == AnimState.RUNNING) {
        // If the animation is already ended then reset the start time
        // in order animation to start again
        if (decayAnimation.isFinishedFromNanos(playTime)) startTime = withFrameNanos { it }

        playTime = withFrameNanos { it } - startTime
        animationValue = decayAnimation.getValueFromNanos(playTime)
      }
    }
  }

  Canvas(
      modifier =
          Modifier.fillMaxWidth()
              .fillMaxHeight()
              .background(Color.Black)
              .clickable(onClick = onClick)) {
    drawCircle(
        color = Color.White, radius = 10f, center = Offset(x = animationValue, y = animationValue))
  }
}

@Preview
@Composable
fun DecayAnimationPreview() {
  DecayAnimationPlayground()
}
