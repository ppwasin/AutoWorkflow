package com.boot.external.appsflyerwrapper.di

import com.appsflyer.AppsFlyerLib
import com.boot.external.appsflyerwrapper.sdk.AppsflyerEventReceiver
import com.boot.external.appsflyerwrapper.utils.AppLifecycleObserver

class AppsflyerLauncherDependencies(
	val appsFlyerLib: AppsFlyerLib,
	val appsflyerReceiver: AppsflyerEventReceiver,
	val launcherActivity: Class<*>,
	val appLifecycleObserver: AppLifecycleObserver
)
