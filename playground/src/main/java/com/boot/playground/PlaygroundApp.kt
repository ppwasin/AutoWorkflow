package com.boot.playground

import android.app.Application
import com.boot.external.appsflyerwrapper.sdk.AppsflyerSDK
import com.boot.external.appsflyerwrapper.ui.AppsflyerProvider
import kotlinx.coroutines.MainScope

class PlaygroundApp : Application(), AppsflyerProvider {
  private val appCoroutineScope = MainScope()
  override val appsflyerSDK by lazy { AppsflyerSDK(this, appCoroutineScope) }
  override fun onCreate() {
    super.onCreate()
    appsflyerSDK.startObserveEvents()
  }
}
