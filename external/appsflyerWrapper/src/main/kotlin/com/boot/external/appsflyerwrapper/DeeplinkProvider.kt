package com.boot.external.appsflyerwrapper

class DeeplinkProvider {
  fun getDeeplinkValue() {
    /*
    If launchmode is standard.
    Use AppsflyerDeelink only if NonOrgnic (defer = false)
    Otherwise use acitvity:intent:data
    =================================================
    Because. AppsflyerDeeplink is not reliable
    - On AppForeground,Background
    => User Click link
    => AppsflyerDeeplinkEvent.Success.
    But sometime no result. Network fail a lot.

    - On AppKill
    => User click link
    => No result from callback

    ^^^ Test with android:launchmode="default"

    =======================================
    If launchmode="singleTop".
    Must set this onNewIntent
    appsflyer.performOnDeepLinking(intent, this)
    ======================================

    Beware onConversionDataSuccess.
    This callback is called with old data such as
    - network fail,
    - open the link from app kill state
    * */

  }
}
