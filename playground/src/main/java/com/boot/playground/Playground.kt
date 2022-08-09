package com.boot.playground

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.boot.playground.animation.AnimAsStatePlayground
import com.boot.playground.animation.AnimatablePlayground
import com.boot.playground.animation.base.AnimationContainer
import com.boot.playground.animation.control.DecayAnimationPlayground
import com.boot.playground.animation.control.TargetBasedAnimationPlayground
import com.boot.playground.animation.lookahead.LookaheadLayoutWithAlignmentLinesDemo
import com.boot.playground.animation.transition.InfiniteTransitionPlayground
import com.boot.playground.async.AsyncPlayground

@Composable
fun Playground() {
  val navController = rememberNavController()
  val routes = remember { PlaygroundRoute.values() }
  Surface(color = MaterialTheme.colors.background) {
    NavHost(navController = navController, startDestination = "initial") {
      composable("initial") {
        LazyColumn(
          horizontalAlignment = Alignment.Start,
          modifier = Modifier.fillMaxSize().padding(16.dp),
        ) {
          routes.forEach { route ->
            item {
              Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate(route.name) },
                shape = RoundedCornerShape(8.dp)
              ) { Text(route.name) }
            }
          }
        }
      }

      routes.map { route -> composable(route.name) { route.Screen() } }
    }
  }
}

enum class PlaygroundRoute {
  Async,
  AnimAsState,
  Animatable,
  Transition,
  TragetAnimation,
  InfiniteTransition,
  DecayAnimation,
  LookAhead;

  @Composable
  fun Screen() {
    return when (this) {
      Async -> AsyncPlayground()
      AnimAsState ->
        AnimationContainer(animRunner = { AnimAsStatePlayground(it) })
      Animatable ->
        AnimationContainer(animRunner = { AnimatablePlayground(it) })
      Transition ->
        AnimationContainer(animRunner = { AnimatablePlayground(it) })
      TragetAnimation -> TargetBasedAnimationPlayground()
      InfiniteTransition -> InfiniteTransitionPlayground()
      DecayAnimation -> DecayAnimationPlayground()
      LookAhead -> LookaheadLayoutWithAlignmentLinesDemo()
    }
  }
}
