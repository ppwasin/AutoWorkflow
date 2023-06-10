package com.boot.dynamicfeature.repository

import com.boot.dynamicfeature.model.SplitInstallEvent
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class SplitInstallEventMapper {
	fun mapSplitStateToEvent(state: SplitInstallSessionState): SplitInstallEvent =
		when (state.status()) {
			SplitInstallSessionStatus.PENDING,
			SplitInstallSessionStatus.DOWNLOADING,
			SplitInstallSessionStatus.DOWNLOADED,
			SplitInstallSessionStatus.INSTALLING -> {
				SplitInstallEvent.InProgress(
					totalBytesToDownload = state.totalBytesToDownload(),
					bytesDownloaded = state.bytesDownloaded(),
				)
			}
			SplitInstallSessionStatus.INSTALLED -> SplitInstallEvent.Success
			SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> SplitInstallEvent.Error.RequireUserConfirmation
			SplitInstallSessionStatus.FAILED -> SplitInstallEvent.Error.Failed(errorCode = mapErrorCode(state.errorCode()))
			SplitInstallSessionStatus.CANCELING -> SplitInstallEvent.Cancel.Canceling
			SplitInstallSessionStatus.CANCELED -> SplitInstallEvent.Cancel.Canceled
			else -> SplitInstallEvent.Unknown(state.toString())
		}


	private fun mapErrorCode(@SplitInstallErrorCode code: Int): String {
		return when (code) {
			SplitInstallErrorCode.NO_ERROR -> "NO_ERROR"
			SplitInstallErrorCode.ACTIVE_SESSIONS_LIMIT_EXCEEDED -> "ACTIVE_SESSIONS_LIMIT_EXCEEDED"
			SplitInstallErrorCode.MODULE_UNAVAILABLE -> "MODULE_UNAVAILABLE"
			SplitInstallErrorCode.INVALID_REQUEST -> "INVALID_REQUEST"
			SplitInstallErrorCode.SESSION_NOT_FOUND -> "SESSION_NOT_FOUND"
			SplitInstallErrorCode.API_NOT_AVAILABLE -> "API_NOT_AVAILABLE"
			SplitInstallErrorCode.NETWORK_ERROR -> "NETWORK_ERROR"
			SplitInstallErrorCode.ACCESS_DENIED -> "ACCESS_DENIED"
			SplitInstallErrorCode.INCOMPATIBLE_WITH_EXISTING_SESSION -> "INCOMPATIBLE_WITH_EXISTING_SESSION"
			SplitInstallErrorCode.INSUFFICIENT_STORAGE -> "INSUFFICIENT_STORAGE"
			SplitInstallErrorCode.SPLITCOMPAT_VERIFICATION_ERROR -> "SPLITCOMPAT_VERIFICATION_ERROR"
			SplitInstallErrorCode.SPLITCOMPAT_EMULATION_ERROR -> "SPLITCOMPAT_EMULATION_ERROR"
			SplitInstallErrorCode.SPLITCOMPAT_COPY_ERROR -> "SPLITCOMPAT_COPY_ERROR"
			SplitInstallErrorCode.PLAY_STORE_NOT_FOUND -> "PLAY_STORE_NOT_FOUND"
			SplitInstallErrorCode.APP_NOT_OWNED -> "APP_NOT_OWNED"
			SplitInstallErrorCode.INTERNAL_ERROR -> "INTERNAL_ERROR"
			else -> code.toString()
		}
	}
}
