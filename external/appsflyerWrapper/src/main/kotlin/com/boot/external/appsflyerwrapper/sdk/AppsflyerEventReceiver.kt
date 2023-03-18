package com.boot.external.appsflyerwrapper.sdk

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.boot.external.appsflyerwrapper.model.AppsFlyerConversionEvent
import com.boot.external.appsflyerwrapper.model.AppsflyerDeeplinkEvent
import com.boot.external.appsflyerwrapper.parser.transformToAppEvent
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AppsflyerEventReceiver(private val appsflyerLib: AppsFlyerLib) {
	suspend fun getUDLEvent(): AppsflyerDeeplinkEvent =
		suspendCancellableCoroutine { cont ->
			appsflyerLib.subscribeForDeepLink { deeplinkRestul ->
				if (cont.isActive) cont.resume(deeplinkRestul.transformToAppEvent())
			}
			cont.invokeOnCancellation { appsflyerLib.subscribeForDeepLink(null) }
		}

	suspend fun getConversionEvent(
		application: Application
	): AppsFlyerConversionEvent = suspendCancellableCoroutine { cont ->
		val listener = AppsFlyerConversionListenerCallback {
			if (cont.isActive) cont.resume(it)
		}
		appsflyerLib.registerConversionListener(application, listener)
		cont.invokeOnCancellation { appsflyerLib.unregisterConversionListener() }
	}
}
