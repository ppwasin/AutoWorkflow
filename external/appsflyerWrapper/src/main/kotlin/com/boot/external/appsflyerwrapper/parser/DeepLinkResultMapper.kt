package com.boot.external.appsflyerwrapper.parser

import com.appsflyer.deeplink.DeepLinkResult
import com.boot.external.appsflyerwrapper.model.AppsflyerDeeplinkEvent

fun DeepLinkResult.transformToAppEvent(): AppsflyerDeeplinkEvent {
  return when (status) {
    DeepLinkResult.Status.FOUND -> AppsflyerDeeplinkEvent.Success(deepLink)
    DeepLinkResult.Status.NOT_FOUND -> AppsflyerDeeplinkEvent.Fail("NOT_FOUND")
    DeepLinkResult.Status.ERROR -> AppsflyerDeeplinkEvent.Fail(error.toString())
  }
}

fun AppsflyerDeeplinkEvent.getDeeplinkValue(): String? {
  return (this as? AppsflyerDeeplinkEvent.Success)?.deeplink?.deepLinkValue
}
