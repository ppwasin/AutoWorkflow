package com.boot.autoworkflow

import android.app.Application
import com.boot.external.appsflyerwrapper.sdk.AppsflyerSDK
import com.boot.external.appsflyerwrapper.ui.AppsflyerProvider
import kotlinx.coroutines.MainScope

class App : Application(), AppsflyerProvider {
  // Lifecycle same as Applicaiton
  private val appCoroutineScope = MainScope()
  override val appsflyerSDK by lazy { AppsflyerSDK(this, appCoroutineScope) }

  override fun onCreate() {
    //    appsflyerSDK.start()
    println("App:onCreate")
    super.onCreate()
    appsflyerSDK.startObserveEvents()
    //        if(hasJunit()) error("!!!Alert!!! Has junit in the build")
  }
}

// val Application.appCoroutineScope: CoroutineScope
//  get() = (this as App).appCoroutineScope
// val Application.appsflyerWrapper: AppsflyerSDK
//  get() = (this as App).appsflyerSDK
