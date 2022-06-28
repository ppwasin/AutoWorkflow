package com.boot.playground.notification

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.boot.playground.PlaygroundActivity

class NotificationDependency(val context: Context, val activity: ComponentActivity)

context(NotificationDependency)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NotificationPlayground() {
    var grantStatus by remember { mutableStateOf<PermerssionStatus?>(null) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
        grantStatus = getPermissionDenyDetails(it, NOTIFICATION_PERMISSION)
    }
    var isRequiredRestart by remember { mutableStateOf(false) }

    LaunchedEffect(context) {
        println("LaunchEffect")
        grantStatus = getGrantStatus(NOTIFICATION_PERMISSION)
    }

//    ComposableLifecycle{ owner, event ->
//        println(event)
//        if(event == Lifecycle.Event.ON_RESUME){
//            println(getGrantStatus(NOTIFICATION_PERMISSION))
//        }
//    }
    Column(Modifier.padding(12.dp)) {
        Text("Permission status: $grantStatus")
        Button(onClick = { launcher.launch(NOTIFICATION_PERMISSION) }) {
            Text("Request Permission")
        }
        Button(
            onClick = {
                revokePermission()
                isRequiredRestart = true
            }
        ) { Text("Revoke Permission") }
        if (isRequiredRestart) Button(onClick = { resetartApp() }) { Text("Restart Application") }
        if(grantStatus == PermerssionStatus.DenyPermanently)
            Button(onClick = { redirectToSystemPermission() }){
                Text("Launch System Permission Settings")
            }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
const val NOTIFICATION_PERMISSION = Manifest.permission.POST_NOTIFICATIONS

context(NotificationDependency)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun getGrantStatus(permission: String): PermerssionStatus {
    return when(ContextCompat.checkSelfPermission(context, permission)){
        PackageManager.PERMISSION_GRANTED -> PermerssionStatus.Grant
        PackageManager.PERMISSION_DENIED -> PermerssionStatus.Deny
        else -> PermerssionStatus.Unknow
    }

}

context(NotificationDependency)
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun revokePermission() {
    context.revokeSelfPermissionOnKill(NOTIFICATION_PERMISSION)
}

context(NotificationDependency)
fun resetartApp() {
    val intent = Intent(activity, PlaygroundActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
    activity.finish()
    Runtime.getRuntime().exit(0)
}

enum class PermerssionStatus {
    Grant, Deny, DenyPermanently, Unknow
}

context(NotificationDependency)
fun getPermissionDenyDetails(isGrant: Boolean, permission: String): PermerssionStatus {
    return if(isGrant) PermerssionStatus.Grant
    else if(activity.shouldShowRequestPermissionRationale(permission)) PermerssionStatus.Deny
    else PermerssionStatus.DenyPermanently
}

context(NotificationDependency)
fun redirectToSystemPermission() {
    val intent = Intent();
    intent.action = ACTION_APPLICATION_DETAILS_SETTINGS
    intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
    intent.data = Uri.fromParts("package", context.packageName, null);
    activity.startActivity(intent)
}

@Composable
fun ComposableLifecycle(
        lifeCycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
        onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {
    DisposableEffect(lifeCycleOwner) {
        val observer = LifecycleEventObserver { source, event ->
            onEvent(source, event)
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
}