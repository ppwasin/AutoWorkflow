package com.boot.autoworkflow.deeplink

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DeeplinkViewModel : ViewModel() {
	var intentData: Uri? by mutableStateOf(null)
	var fromOnNewIntent: String by mutableStateOf("")
}
