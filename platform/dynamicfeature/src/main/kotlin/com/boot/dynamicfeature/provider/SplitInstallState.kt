package com.boot.dynamicfeature.provider

import com.google.android.play.core.splitinstall.SplitInstallSessionState


sealed interface SplitInstallState {
	enum class MainState{
		Download, Install, Cancel;
	}
	data class InProgress(val mainState: MainState, val state: SplitInstallSessionState): SplitInstallState
	data class Done(val mainState: MainState, val state: SplitInstallSessionState): SplitInstallState
	data class RequireUserConfirmation(val state: SplitInstallSessionState) : SplitInstallState
	data class Failed(val state: SplitInstallSessionState) : SplitInstallState
	data class Pending(val state: SplitInstallSessionState) : SplitInstallState
	data class Unknown(val state: SplitInstallSessionState) : SplitInstallState

	data class Error(val error: Throwable): SplitInstallState
}
