package com.boot.designsystem.theme.app

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimensions(
	val paddingSmall: Dp = 4.dp,
	val paddingMedium: Dp = 8.dp,
	val paddingLarge: Dp = 16.dp,
	val AppBarCollapsedHeight: Dp = 56.dp,
	val AppBarExpendedHeight: Dp = 400.dp
)

internal val LocalDimensions = staticCompositionLocalOf { AppDimensions() }
