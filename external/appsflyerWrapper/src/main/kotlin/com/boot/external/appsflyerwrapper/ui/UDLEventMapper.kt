package com.boot.external.appsflyerwrapper.ui

import com.boot.external.appsflyerwrapper.model.AppsflyerDeeplinkEvent

fun AppsflyerDeeplinkEvent.getAtribute(key: String): String? {
  if (this !is AppsflyerDeeplinkEvent.Success) return null
  return this.deeplink.getStringValue(key)
}
