package com.boot.external.appsflyerwrapper.sdk

import android.app.Activity
import android.app.Application
import android.content.Intent

context(Application)

fun createAppsflyerSDK(): AppsflyerInitializer {
  return AppsflyerInitializer(this@Application, AppsflyerConstant.DEV_KEY)
}

context(Activity)

fun setupForAppsflyerOnNewIntent(intent: Intent?) {
  setIntent(intent)
}
