package com.boot.external.appsflyerwrapper.appsetup

import android.app.Activity
import android.app.Application
import android.content.Intent
import com.boot.external.appsflyerwrapper.sdk.AppsflyerSDK

context(Application)

fun setupForAppsflyerOnCreate(sdk: AppsflyerSDK) {
  sdk.startObserveEvents()
}

context(Activity)

fun setupForAppsflyerOnNewIntent(intent: Intent?) {
  setIntent(intent)
}
