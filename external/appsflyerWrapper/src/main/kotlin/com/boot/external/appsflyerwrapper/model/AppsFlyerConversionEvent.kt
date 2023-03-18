package com.boot.external.appsflyerwrapper.model

sealed interface AppsFlyerConversionEvent {
	data class ConversionDataSuccess(val conversionData: Map<String, Any>?) :
		AppsFlyerConversionEvent

	data class ConversionDataFail(val reason: String?) : AppsFlyerConversionEvent
	data class AppOpenAttribution(val attributes: Map<String, String>?) :
		AppsFlyerConversionEvent

	data class AttributionFailure(val reason: String?) : AppsFlyerConversionEvent
}
