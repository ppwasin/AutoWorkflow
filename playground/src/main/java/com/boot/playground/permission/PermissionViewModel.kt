package com.boot.playground.permission

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class PermissionViewModel : ViewModel() {
	val launchPremissionRequest = MutableSharedFlow<PermissionAction>()

	fun tapRequestPermission() {
		viewModelScope.launch {
			launchPremissionRequest.emit(PermissionAction.RequestPermission)
		}
	}

	fun tapGotoSettings() {
		viewModelScope.launch {
			launchPremissionRequest.emit(PermissionAction.GoToSettings)
		}
	}
}
