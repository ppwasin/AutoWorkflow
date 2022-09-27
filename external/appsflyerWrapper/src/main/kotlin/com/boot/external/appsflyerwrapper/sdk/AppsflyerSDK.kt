package com.boot.external.appsflyerwrapper.sdk

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.appsflyer.deeplink.DeepLinkResult
import com.boot.external.appsflyerwrapper.AppsflyerLogger
import com.boot.external.appsflyerwrapper.model.AppsFlyerConversionEvent
import com.boot.external.appsflyerwrapper.model.AppsflyerDeeplinkEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class AppsflyerSDK(
  private val application: Application,
  private val coroutineScope: CoroutineScope
) {
  private val appsflyer: AppsFlyerLib by lazy { AppsFlyerLib.getInstance() }
  private val conversionListener =
    AppsFlyerConversionListenerImpl(coroutineScope)
  private val requestEvent = AppsFlyerRequestListenerImpl(coroutineScope)
  private val logger = AppsflyerLogger()
  private val deelinkEvents = MutableSharedFlow<AppsflyerDeeplinkEvent>(1)

  fun startObserveEvents() {
    logger.debug("startObserveEvents")
    with(appsflyer) {
      init(AppsflyerConstant.DEV_KEY, conversionListener, application)
      setDebugLog(true)
      start(application, AppsflyerConstant.DEV_KEY, requestEvent)
      registerConversionListener(application, conversionListener)

      internalObserveDeeplinkEvents()
        .onEach { logger.debug("Before send channel: $it") }
        .onEach(deelinkEvents::emit)
        .launchIn(coroutineScope)
      requestEvent
        .obseve()
        .onEach { logger.debug("Request: $it") }
        .launchIn(coroutineScope)
    }
  }

  fun observeDeeplinkEvents(): Flow<AppsflyerDeeplinkEvent> {
    return deelinkEvents
  }
  fun observeLegacyDeeplinkEvents(): Flow<AppsFlyerConversionEvent?> {
    return conversionListener.observe()
  }

  private fun AppsFlyerLib.internalObserveDeeplinkEvents():
    Flow<AppsflyerDeeplinkEvent> = callbackFlow {
    subscribeForDeepLink(
      {
        val event = transform(it)
        logger.debug(it.toString())
        launch { send(event) }
      },
      100
    )
    awaitClose()
  }

  private fun transform(result: DeepLinkResult): AppsflyerDeeplinkEvent {
    return when (result.status) {
      DeepLinkResult.Status.FOUND ->
        AppsflyerDeeplinkEvent.Success(result.deepLink)
      DeepLinkResult.Status.NOT_FOUND -> AppsflyerDeeplinkEvent.NotFound
      DeepLinkResult.Status.ERROR -> AppsflyerDeeplinkEvent.Fail(result.error)
    }
  }
}
