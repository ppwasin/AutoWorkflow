package com.boot.external.appsflyerwrapper.model

sealed interface AppsFlyerConversionEvent {
  data class ConversionDataSuccess(
    val conversionData: MutableMap<String, Any>?
  ) : AppsFlyerConversionEvent
  data class ConversionDataFail(val reason: String?) : AppsFlyerConversionEvent
  data class AppOpenAttribution(val attributes: MutableMap<String, String>?) :
    AppsFlyerConversionEvent
  data class AttributionFailure(val reason: String?) : AppsFlyerConversionEvent
}
