package com.boot.playground.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.boot.designsystem.theme.material.AppMaterialTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(
  permission: String = Manifest.permission.POST_NOTIFICATIONS,
  viewModel: PermissionViewModel = viewModel(),
  content: @Composable () -> Unit
) {

  val context = LocalContext.current
  val permissionDatastore = rememberPermissionDatastore(context)
  val permissionState =
    rememberPermissionState(permission) {
      println("User action with soft-prompt (Activity Permission Result)")
      permissionDatastore.update(it)
    }
  //  rememberLifecycleEvent(
  //    doOnStart = { permissionState.launchPermissionRequest() },
  //  )
  LaunchedEffect(Unit) {
    viewModel.launchPremissionRequest
      .onEach {
        when (it) {
          PermissionAction.RequestPermission -> {
            permissionState.launchPermissionRequest()
          }
          PermissionAction.GoToSettings -> redirectToSystemPermission(context)
        }
      }
      .launchIn(this)
  }

  Column(
    modifier = Modifier.padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    val status = permissionState.status
    KeyValue("CheckSelfPermission", context.selfCheckEnum(permission))
    KeyValue("App status", status)
    KeyValue("User action", permissionDatastore.userActionWithSoftprompt)

    when {
      // If the permission is granted, then show screen with the feature enabled
      status == PermissionStatus.Granted -> {
        content()
      }
      // only true when user click don't allow for the first time
      //      status.shouldShowRationale -> {
      permissionDatastore.userActionWithSoftprompt == null ||
        status.shouldShowRationale -> {
        Button(onClick = viewModel::tapRequestPermission) {
          Text("Turn on Notification")
        }
      }
      else -> {
        Text("Go to settings and then select: Permission  > Allow Permission")
        Button(onClick = viewModel::tapGotoSettings) { Text("Go to Settings") }
      }
    }
  }
}

fun redirectToSystemPermission(context: Context) {
  val intent = Intent()
  intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
  intent.data = Uri.fromParts("package", context.packageName, null)
  context.findActivity().startActivity(intent)
}

internal fun Context.findActivity(): Activity {
  var context = this
  while (context is ContextWrapper) {
    if (context is Activity) return context
    context = context.baseContext
  }
  throw IllegalStateException(
    "Permissions should be called in the context of an Activity"
  )
}

internal fun Context.selfCheckEnum(permission: String): String? {
  return when (ContextCompat.checkSelfPermission(this, permission)) {
    PackageManager.PERMISSION_GRANTED -> "PERMISSION_GRANTED"
    PackageManager.PERMISSION_DENIED -> "PERMISSION_DENIED"
    else -> null
  }
}

@Composable
fun KeyValue(key: String, value: Any?) {
  Column {
    Text(text = key, fontWeight = FontWeight.Bold)
    Text(text = value.toString())
  }
}

@Preview
@Composable
fun PermissionScreenTest() {
  AppMaterialTheme { PermissionScreen { Text(text = "test") } }
}
