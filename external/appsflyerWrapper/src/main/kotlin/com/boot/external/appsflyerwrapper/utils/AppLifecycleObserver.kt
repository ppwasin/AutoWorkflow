package com.boot.external.appsflyerwrapper.utils

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.util.Stack
import kotlinx.coroutines.flow.MutableStateFlow

class AppLifecycleObserver : DefaultLifecycleObserver {
  enum class AppState {
    Foreground,
    Background
  }

  // Foreground > Background => Click link
  // Background > Foreground | Foreground => Click link (UDL doesn't work)
  val appState = MutableStateFlow<AppState?>(null)
  val appStateStack = MutableStateFlow<Stack<AppState>>(Stack())
  override fun onStart(owner: LifecycleOwner) {
    super.onStart(owner)
    appState.tryEmit(AppState.Foreground)
    appStateStack.value
  }

  override fun onStop(owner: LifecycleOwner) {
    super.onStop(owner)
    appState.tryEmit(AppState.Background)
  }
}
