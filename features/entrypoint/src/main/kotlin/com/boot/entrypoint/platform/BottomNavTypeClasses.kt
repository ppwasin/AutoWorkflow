package com.boot.entrypoint.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

interface BottomNavTypeClasses<T> {
  fun T.route(): String
  fun T.label(): String
  fun T.icon(): ImageVector
  @Composable fun T.Screen()
}
