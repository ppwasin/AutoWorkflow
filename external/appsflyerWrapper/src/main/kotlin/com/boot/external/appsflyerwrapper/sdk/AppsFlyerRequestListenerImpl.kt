package com.boot.external.appsflyerwrapper.sdk

import com.appsflyer.attribution.AppsFlyerRequestListener
import com.boot.external.appsflyerwrapper.model.AppsFlyerRequestEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class AppsFlyerRequestListenerImpl(private val scope: CoroutineScope) :
  AppsFlyerRequestListener {
  private val requestEvents = MutableSharedFlow<AppsFlyerRequestEvent>(1)
  override fun onSuccess() {
    scope.launch { requestEvents.emit(AppsFlyerRequestEvent.Success) }
  }

  override fun onError(p0: Int, p1: String) {
    scope.launch { requestEvents.emit(AppsFlyerRequestEvent.Error(p0, p1)) }
  }

  fun obseve(): Flow<AppsFlyerRequestEvent> = requestEvents
}
