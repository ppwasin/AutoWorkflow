package com.boot.playground

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.boot.playground.animation.AnimationContainer
import com.boot.playground.animation.TargetBasedAnimationPlayground
import com.boot.playground.animation.transition.InfiniteTransitionPlayground
import com.boot.playground.animation.transition.TransitionPlayground

@Composable
fun Playground() {
  Surface(color = MaterialTheme.colors.background) {
    //    AsyncPlayground()
    //    AnimationContainer(animRunner = { AnimAsStatePlayground(it) })
    //    AnimationContainer(animRunner = { AnimatablePlayground(it) })
//    AnimationContainer { TransitionPlayground(it) }
//    InfiniteTransitionPlayground()
    TargetBasedAnimationPlayground()
  }
}
