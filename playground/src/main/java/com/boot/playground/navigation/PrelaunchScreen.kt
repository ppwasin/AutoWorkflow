package com.boot.playground.navigation

import androidx.compose.runtime.Composable

@Composable fun PrelaunchScreen() {}

class PrelaunchRoot() {
  sealed interface NavTarget {
    object China : NavTarget
    object Skorea : NavTarget
    object Login : NavTarget
    object Cookie : NavTarget
  }

  @Composable fun Render() {}
}
