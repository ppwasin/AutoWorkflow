package com.boot.playground.animation.transition

import androidx.compose.animation.core.RepeatMode.Reverse
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import com.boot.playground.animation.base.CanvasWrapper

//rememberInfiniteTransition
@Composable
fun InfiniteTransitionPlayground() {
    val infiniteTransition = rememberInfiniteTransition()
    val infinitelyAnimatedFloat = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(),
            // The value will infinitely repeat from 0 to 1 and 1 to 0
            repeatMode = Reverse
        )
    )

    CanvasWrapper(radius = 10f, alpha = infinitelyAnimatedFloat.value)
}