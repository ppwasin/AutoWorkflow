package com.boot.external.appsflyerwrapper.model

data class DeeplinkEvent(
  val isFirstLaunch: Boolean,
  // Organic: Defer = false, NonOrganic: Defer = true
  val isDefer: Boolean
)
