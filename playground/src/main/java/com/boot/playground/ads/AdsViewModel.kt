package com.boot.playground.ads

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdsViewModel(private val application: Application) : ViewModel() {
	var adsId by mutableStateOf("")

	init {
		fetchId()
	}

	fun fetchId() {
		viewModelScope.launch(Dispatchers.IO) {
			adsId = AdvertisingIdClient.getAdvertisingIdInfo(application).id ?: ""
		}
	}
}
