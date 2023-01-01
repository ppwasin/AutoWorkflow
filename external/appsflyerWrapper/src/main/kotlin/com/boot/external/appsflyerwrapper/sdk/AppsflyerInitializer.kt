package com.boot.external.appsflyerwrapper.sdk

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.appsflyer.AppsFlyerProperties

class AppsflyerInitializer
constructor(
  private val application: Application,
  private val appsflyerKey: String,
) {
  val sdk: AppsFlyerLib by lazy { AppsFlyerLib.getInstance() }
  fun initializeAppsflyer() {
    with(sdk) {
      enableFacebookDeferredApplinks(true)
      init(appsflyerKey, null, application)
      setDebugLog(true)
      start(application, appsflyerKey, null)

      AppsFlyerProperties.getInstance()
        .set(
          AppsFlyerProperties.USE_HTTP_FALLBACK,
          true,
        ) // Enable HTTPS to HTTP fallback
    }
  }
}
