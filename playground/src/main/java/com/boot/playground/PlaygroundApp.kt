package com.boot.playground

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.boot.external.appsflyerwrapper.di.AppsflyerLauncherDependencies
import com.boot.external.appsflyerwrapper.di.HasAppsflyerLauncherDependencies
import com.boot.external.appsflyerwrapper.sdk.AppsflyerEventReceiver
import com.boot.external.appsflyerwrapper.sdk.createAppsflyerSDK
import com.boot.external.appsflyerwrapper.utils.AppLifecycleObserver

class PlaygroundApp : Application(), HasAppsflyerLauncherDependencies {
	private val appsflyerSDK by lazy { createAppsflyerSDK() }
	private val appLifecycleObserver by lazy { AppLifecycleObserver() }
	override fun onCreate() {
		super.onCreate()
		appsflyerSDK.initializeAppsflyer()
		ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver)
	}

	override fun getAppsflyerLauncherDependencies():
		AppsflyerLauncherDependencies {
		return AppsflyerLauncherDependencies(
			appsflyerReceiver = AppsflyerEventReceiver(appsflyerSDK.sdk),
			launcherActivity = PlaygroundActivity::class.java,
			appLifecycleObserver = appLifecycleObserver,
			appsFlyerLib = appsflyerSDK.sdk,
		)
	}
}
