package com.boot.external.appsflyerwrapper.sdk

import com.appsflyer.AppsFlyerConversionListener
import com.boot.external.appsflyerwrapper.model.AppsFlyerConversionEvent

class AppsFlyerConversionListenerCallback(
  private val callback: (AppsFlyerConversionEvent) -> Unit
) : AppsFlyerConversionListener {
  override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
    callback(AppsFlyerConversionEvent.ConversionDataSuccess(p0))
  }

  override fun onConversionDataFail(p0: String?) {
    callback(AppsFlyerConversionEvent.ConversionDataFail(p0))
  }

  override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
    callback(AppsFlyerConversionEvent.AppOpenAttribution(p0))
  }

  override fun onAttributionFailure(p0: String?) {
    callback(AppsFlyerConversionEvent.AttributionFailure(p0))
  }
}
