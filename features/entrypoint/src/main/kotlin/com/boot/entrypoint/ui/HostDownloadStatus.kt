package com.boot.entrypoint.ui

sealed interface HostDownloadStatus {
    data class InProgress(val percentage: String) : HostDownloadStatus
    data object Error : HostDownloadStatus
    data object Succees : HostDownloadStatus
}
