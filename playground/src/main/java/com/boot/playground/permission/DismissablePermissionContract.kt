package com.boot.playground.permission

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

class DismissablePermissionContract :
  ActivityResultContract<String, Boolean?>() {
  override fun createIntent(context: Context, input: String): Intent {
    return createIntent(arrayOf(input))
  }

  @Suppress("AutoBoxing")
  override fun parseResult(resultCode: Int, intent: Intent?): Boolean? {
    if (intent == null || resultCode != Activity.RESULT_OK) return false
    val grantResults =
      intent.getIntArrayExtra(
        ActivityResultContracts.RequestMultiplePermissions
          .EXTRA_PERMISSION_GRANT_RESULTS,
      )
    return grantResults?.any { result ->
      result == PackageManager.PERMISSION_GRANTED
    } == true
  }

  override fun getSynchronousResult(
    context: Context,
    input: String
  ): SynchronousResult<Boolean?>? {
    val granted =
      ContextCompat.checkSelfPermission(context, input) ==
        PackageManager.PERMISSION_GRANTED
    return if (granted) {
      SynchronousResult(true)
    } else {
      // proceed with permission request
      null
    }
  }

  internal fun createIntent(input: Array<String>): Intent {
    return Intent(
        ActivityResultContracts.RequestMultiplePermissions
          .ACTION_REQUEST_PERMISSIONS,
      )
      .putExtra(
        ActivityResultContracts.RequestMultiplePermissions.EXTRA_PERMISSIONS,
        input,
      )
  }
}
