package com.boot.playground.animation.control

import androidx.compose.animation.core.DecayAnimation
import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.boot.playground.animation.control.utils.ControlCanvas
import com.boot.playground.animation.control.utils.generateOnClickAnimation

@Composable
fun DecayAnimationPlayground() {
  val decayAnimation = remember {
    DecayAnimation(
      animationSpec =
        FloatExponentialDecaySpec(
          // How quick the animation will stop
          frictionMultiplier = 0.7f,
        ),
      initialValue = 0f,
      initialVelocity = 2000f,
    )
  }

  val animationCtrl = generateOnClickAnimation(decayAnimation)

  ControlCanvas(
    onClick = animationCtrl.onClick,
    centerOffset = animationCtrl.offset,
  )
}

@Preview
@Composable
fun DecayAnimationPreview() {
  DecayAnimationPlayground()
}
