package com.boot.external.appsflyerwrapper.sdk

import com.appsflyer.AppsFlyerConversionListener
import com.boot.external.appsflyerwrapper.AppsflyerLogger
import com.boot.external.appsflyerwrapper.model.AppsFlyerConversionEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class AppsFlyerConversionListenerImpl(private val scope: CoroutineScope) :
  AppsFlyerConversionListener {
  private val logger = AppsflyerLogger()
  private val deeplinkEventChannel =
    MutableSharedFlow<AppsFlyerConversionEvent>(1)
  override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
    logger.debug("onConversionDataSuccess: $p0")
    scope.launch {
      deeplinkEventChannel.emit(
        AppsFlyerConversionEvent.ConversionDataSuccess(p0)
      )
    }
  }

  override fun onConversionDataFail(p0: String?) {
    logger.debug("onConversionDataFail: $p0")
    scope.launch {
      deeplinkEventChannel.emit(AppsFlyerConversionEvent.ConversionDataFail(p0))
    }
  }

  override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
    logger.debug("onAppOpenAttribution: $p0")
    scope.launch {
      deeplinkEventChannel.emit(AppsFlyerConversionEvent.AppOpenAttribution(p0))
    }
  }

  override fun onAttributionFailure(p0: String?) {
    logger.debug("onAttributionFailure: $p0")
    scope.launch {
      deeplinkEventChannel.emit(AppsFlyerConversionEvent.AttributionFailure(p0))
    }
  }

  fun observe(): Flow<AppsFlyerConversionEvent> = deeplinkEventChannel
}
