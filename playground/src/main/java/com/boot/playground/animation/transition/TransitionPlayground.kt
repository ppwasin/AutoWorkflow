package com.boot.playground.animation.transition

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.boot.playground.animation.base.AnimationContainer
import com.boot.playground.animation.base.CanvasWrapper

// updateTransition
@Composable
fun TransitionPlayground(isVisible: Boolean) {

  // Any state change will trigger animations which
  // are created with this transition to the new state
  val transition = updateTransition(targetState = isVisible, label = "transition")
  val circleAlpha =
      transition.animateFloat(transitionSpec = { tween(durationMillis = 3000) }, label = "circleAlpha") {
        if (it) 0f else 1f
      }

  val circleRadius =
      transition.animateFloat(transitionSpec = { tween(durationMillis = 3000) }, label = "circleRadius") {
        if (it) 10f else 50f
      }


  CanvasWrapper(radius = circleRadius.value, alpha = circleAlpha.value)
}

@Composable
@Preview
fun TransitionPlaygroundPreview() {
    AnimationContainer { TransitionPlayground(it) }
}