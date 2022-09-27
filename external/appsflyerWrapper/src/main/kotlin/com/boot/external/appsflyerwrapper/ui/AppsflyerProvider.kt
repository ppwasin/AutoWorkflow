package com.boot.external.appsflyerwrapper.ui

import com.boot.external.appsflyerwrapper.sdk.AppsflyerSDK

interface AppsflyerProvider {
  val appsflyerSDK: AppsflyerSDK
}
