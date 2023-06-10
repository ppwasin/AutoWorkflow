package com.boot.dynamicfeature.model

sealed interface TerminatedEvent
sealed interface SplitInstallEvent: DynamicFeatureEvent {
	data class InProgress(val totalBytesToDownload: Long, val bytesDownloaded: Long): SplitInstallEvent
	object Success: SplitInstallEvent, TerminatedEvent
	sealed interface Error: SplitInstallEvent, TerminatedEvent {
		object RequireUserConfirmation : Error
		data class Failed(val errorCode: String): Error
	}
	sealed interface Cancel: SplitInstallEvent {
		object Canceling: Cancel
		object Canceled: Cancel, TerminatedEvent
	}
	data class Unknown(val rawState: String): SplitInstallEvent
}
