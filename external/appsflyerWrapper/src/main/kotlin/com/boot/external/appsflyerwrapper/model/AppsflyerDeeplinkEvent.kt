package com.boot.external.appsflyerwrapper.model

import com.appsflyer.deeplink.DeepLink
import com.appsflyer.deeplink.DeepLinkResult

sealed interface AppsflyerDeeplinkEvent {
  object NotFound : AppsflyerDeeplinkEvent
  data class Fail(val error: DeepLinkResult.Error) : AppsflyerDeeplinkEvent
  data class Success(val deeplink: DeepLink) : AppsflyerDeeplinkEvent
}
