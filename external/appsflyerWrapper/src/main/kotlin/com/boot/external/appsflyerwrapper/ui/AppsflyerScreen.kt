package com.boot.external.appsflyerwrapper.ui

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.boot.designsystem.theme.material.AppMaterialTheme
import com.boot.external.appsflyerwrapper.sdk.AppsflyerSDK
import com.boot.external.appsflyerwrapper.utils.rememberLifecycleEvent
import kotlinx.coroutines.launch

@Composable
fun AppsflyerScreen(appsflyerSdk: AppsflyerSDK) {
  val context = LocalContext.current
  //  LaunchedEffect(Unit){
  //    appsflyerSdk.startObserveEvents()
  //  }
  //  val appsflyerEvent =
  //    getOnLifecycler(get = { appsflyerSdk.observeDeeplinkEvents().first() })
  val appsflyerEvent by
    appsflyerSdk.observeDeeplinkEvents().collectAsState(null)
  val appsflyerLegacyEvent by
    appsflyerSdk.observeLegacyDeeplinkEvents().collectAsState(null)
  val intentData = (context as Activity).intent.data
  //  var actualNavigationData by remember { mutableStateOf("") }
  //  if(appsflyerEvent == null) actualNavigationData = intentData.toString()
  //  else actualNavigationData = appsflyerEvent.toString()

  //  getOnLifecycler(get = {
  //    val appsflyerData = withTimeout(3000L){
  //      appsflyerSdk.observeDeeplinkEvents().first()
  //    }
  //  })

  AppMaterialTheme {
    Surface {
      Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier =
          Modifier.fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
      ) {
        Text("Intent Data", fontWeight = FontWeight.Bold)
        Text(intentData.toString())

        Divider()

        Text("UDL Callback", fontWeight = FontWeight.Bold)
        Text("$appsflyerEvent")

        Divider()

        Text("Legacy Event", fontWeight = FontWeight.Bold)
        Text("$appsflyerLegacyEvent")

        Divider()
      }
    }
  }
}

@Composable
fun <T> getOnLifecycler(
  matchEvent: Lifecycle.Event = Lifecycle.Event.ON_RESUME,
  get: suspend () -> T
): T? {
  val coroutine = rememberCoroutineScope()
  var event by remember { mutableStateOf<T?>(null) }
  val lifecycle = LocalLifecycleOwner.current.lifecycle
  rememberLifecycleEvent(lifecycle) {
    coroutine.launch { event = if (it == matchEvent) get() else null }
  }

  return event
}
