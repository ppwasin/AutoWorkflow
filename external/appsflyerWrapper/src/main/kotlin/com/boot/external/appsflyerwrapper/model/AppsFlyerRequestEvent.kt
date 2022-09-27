package com.boot.external.appsflyerwrapper.model

sealed interface AppsFlyerRequestEvent {
  object Success : AppsFlyerRequestEvent
  data class Error(val code: Int, val message: String) : AppsFlyerRequestEvent
}
