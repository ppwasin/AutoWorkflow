package com.boot.external.appsflyerwrapper.model

import com.appsflyer.deeplink.DeepLink

sealed interface AppsflyerDeeplinkEvent {
  data class Fail(val reason: String) : AppsflyerDeeplinkEvent
  data class Success(val deeplink: DeepLink) : AppsflyerDeeplinkEvent
}
