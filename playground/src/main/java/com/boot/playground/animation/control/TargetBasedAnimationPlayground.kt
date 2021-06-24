package com.boot.playground.animation.control

import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.boot.playground.animation.control.utils.ControlCanvas
import com.boot.playground.animation.control.utils.generateOnClickAnimation

@Composable
fun TargetBasedAnimationPlayground() {
  val targetBasedAnimation = remember {
    TargetBasedAnimation(
        animationSpec = tween(2000),
        typeConverter = Float.VectorConverter,
        initialValue = 0f,
        targetValue = 1000f)
  }
  val animationCtrl = generateOnClickAnimation(targetBasedAnimation)

  ControlCanvas(onClick = animationCtrl.onClick, centerOffset = animationCtrl.offset)
}

@Preview
@Composable
fun TargetBasedAnimationPreview() {
  TargetBasedAnimationPlayground()
}
