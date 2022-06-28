package com.boot.playground.notification

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun notificationPlaygroundDiProvider(): NotificationDependency {
    val context = LocalContext.current
    return NotificationDependency(context, context as ComponentActivity)
}
