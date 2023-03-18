package com.boot.entrypoint.platform

import androidx.compose.ui.graphics.vector.ImageVector

interface BottomNavInfoGetter<T> {
	fun getRoute(item: T): String
	fun getLabel(item: T): String
	fun getIcon(item: T): ImageVector
}
