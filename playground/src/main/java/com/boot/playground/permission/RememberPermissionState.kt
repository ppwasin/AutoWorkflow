package com.boot.playground.permission

//
// @Composable
// fun rememberPermissionState(permission: String){
//  val context = LocalContext.current
//  val permissionState = remember(permission) {
//    MutablePermissionState(permission, context, context.findActivity())
//  }
//
//  val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
//    permissionState.refreshPermissionStatus()
//    onPermissionResult(it)
//  }
//
//  val requestPermissionLauncher =
//    registerForActivityResult(
//      ActivityResultContracts.RequestMultiplePermissions()
//    ) { result: MutableMap<String, Boolean> ->
//      val deniedList: List<String> = result.filter {
//        !it.value
//      }.map {
//        it.key
//      }
//
//      when {
//        deniedList.isNotEmpty() -> {
//          val map = deniedList.groupBy { permission ->
//            if (shouldShowRequestPermissionRationale(permission)) DENIED else EXPLAINED
//          }
//          map[DENIED]?.let {
//            // request denied , request again
//          }
//          map[EXPLAINED]?.let {
//            //request denied ,send to settings
//
//          }
//
//        }
//        else -> {
//          //All request are permitted
//        }
//      }
//    }
// }
//
// @Stable
// sealed interface PermissionStatus {
//  object Undertermined: PermissionStatus
//  object Granted : PermissionStatus
//  data class Denied(
//    val shouldShowRationale: Boolean
//  ) : PermissionStatus
// }
//
// @Stable
// internal class MutablePermissionState(
//  val permission: String,
//  private val context: Context,
//  private val activity: Activity
// )  {
//
//  var status: PermissionStatus by mutableStateOf(getPermissionStatus())
//
//  fun launchPermissionRequest() {
//    launcher?.launch(
//      permission
//    ) ?: throw IllegalStateException("ActivityResultLauncher cannot be null")
//  }
//
//  internal var launcher: ActivityResultLauncher<String>? = null
//
//  internal fun refreshPermissionStatus() {
//    status = getPermissionStatus()
//  }
//
//  private fun getPermissionStatus(): PermissionStatus {
//    val hasPermission = context.checkPermission(permission)
//    return if (hasPermission) {
//      PermissionStatus.Granted
//    } else {
//      PermissionStatus.Denied(activity.shouldShowRationale(permission))
//    }
//  }
// }
//
// internal fun Context.checkPermission(permission: String): Boolean {
//  return ContextCompat.checkSelfPermission(this, permission) ==
//    PackageManager.PERMISSION_GRANTED
// }
//
// internal fun Activity.shouldShowRationale(permission: String): Boolean {
//  return ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
// }
