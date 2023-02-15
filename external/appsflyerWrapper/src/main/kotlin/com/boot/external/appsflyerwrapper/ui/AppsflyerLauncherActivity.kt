package com.boot.external.appsflyerwrapper.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.boot.external.appsflyerwrapper.AppsflyerLogger
import com.boot.external.appsflyerwrapper.di.AppsflyerLauncherDependencies
import com.boot.external.appsflyerwrapper.di.HasAppsflyerLauncherDependencies
import com.boot.external.appsflyerwrapper.parser.getDeeplinkValue
import com.boot.external.appsflyerwrapper.sdk.setupForAppsflyerOnNewIntent
import com.boot.external.appsflyerwrapper.utils.AppLifecycleObserver
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class AppsflyerLauncherActivity : ComponentActivity() {
  private val logger = AppsflyerLogger()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    with(getDependenceis()) {
      lifecycleScope.launch {

        // Workaround when launch the link via adb and app is on foreground
        val appState = appLifecycleObserver.appState.first()
        logger.debug("Intent.data ${intent.data} on $appState")
        if (appState == AppLifecycleObserver.AppState.Foreground) {
          logger.debug("manual perform")
          appsFlyerLib.performOnDeepLinking(
            intent,
            this@AppsflyerLauncherActivity,
          )
        }

        var deeplinkValue: String? = null
        try {
          withTimeout(1000) {
            deeplinkValue = appsflyerReceiver.getUDLEvent().getDeeplinkValue()
          }
        } catch (ex: Exception) {} finally {
          logger.debug("deeplinkValue: $deeplinkValue")
          val intent = createIntent(deeplinkValue, launcherActivity)
          startActivity(intent)
          finish()
        }
      }
    }
  }

  private fun createIntent(
    deeplinkValue: String?,
    launcherActivity: Class<*>
  ): Intent {
    return Intent().apply {
      setClass(this@AppsflyerLauncherActivity, launcherActivity)
      deeplinkValue?.let { data = Uri.parse(it) }
      flags =
        Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or
          Intent.FLAG_ACTIVITY_CLEAR_TASK or
          Intent.FLAG_ACTIVITY_NEW_TASK
    }
  }

  private fun getDependenceis(): AppsflyerLauncherDependencies {
    return (application as HasAppsflyerLauncherDependencies)
      .getAppsflyerLauncherDependencies()
  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    logger.debug("onNewIntent")
    setupForAppsflyerOnNewIntent(intent)
  }
}
