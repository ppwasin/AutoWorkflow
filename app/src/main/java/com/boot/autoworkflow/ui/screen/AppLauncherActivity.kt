package com.boot.autoworkflow.ui.screen

import android.content.Intent
import androidx.activity.ComponentActivity

class AppLauncherActivity : ComponentActivity() {
  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    setIntent(intent)
    println("[Appsflyer] onNewIntent ${intent?.data}")
  }
}
