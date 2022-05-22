package com.boot.designsystem.theme.material

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.CheckboxDefaults.colors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.boot.designSystem.R


private val LightThemeColors =
  lightColorScheme(
    primary = Color(R.color.md_theme_light_primary),
    onPrimary = Color(R.color.md_theme_light_onPrimary),
    primaryContainer = Color(R.color.md_theme_light_primaryContainer),
    onPrimaryContainer = Color(R.color.md_theme_light_onPrimaryContainer),
    secondary = Color(R.color.md_theme_light_secondary),
    onSecondary = Color(R.color.md_theme_light_onSecondary),
    secondaryContainer = Color(R.color.md_theme_light_secondaryContainer),
    onSecondaryContainer = Color(R.color.md_theme_light_onSecondaryContainer),
    tertiary = Color(R.color.md_theme_light_tertiary),
    onTertiary = Color(R.color.md_theme_light_onTertiary),
    tertiaryContainer = Color(R.color.md_theme_light_tertiaryContainer),
    onTertiaryContainer = Color(R.color.md_theme_light_onTertiaryContainer),
    error = Color(R.color.md_theme_light_error),
    errorContainer = Color(R.color.md_theme_light_errorContainer),
    onError = Color(R.color.md_theme_light_onError),
    onErrorContainer = Color(R.color.md_theme_light_onErrorContainer),
    background = Color(R.color.md_theme_light_background),
    onBackground = Color(R.color.md_theme_light_onBackground),
    surface = Color(R.color.md_theme_light_surface),
    onSurface = Color(R.color.md_theme_light_onSurface),
    surfaceVariant = Color(R.color.md_theme_light_surfaceVariant),
    onSurfaceVariant = Color(R.color.md_theme_light_onSurfaceVariant),
    outline = Color(R.color.md_theme_light_outline),
    inverseOnSurface = Color(R.color.md_theme_light_inverseOnSurface),
    inverseSurface = Color(R.color.md_theme_light_inverseSurface),
    inversePrimary = Color(R.color.md_theme_light_inversePrimary),
  )
private val DarkThemeColors =
  darkColorScheme(
    primary = Color(R.color.md_theme_dark_primary),
    onPrimary = Color(R.color.md_theme_dark_onPrimary),
    primaryContainer = Color(R.color.md_theme_dark_primaryContainer),
    onPrimaryContainer = Color(R.color.md_theme_dark_onPrimaryContainer),
    secondary = Color(R.color.md_theme_dark_secondary),
    onSecondary = Color(R.color.md_theme_dark_onSecondary),
    secondaryContainer = Color(R.color.md_theme_dark_secondaryContainer),
    onSecondaryContainer = Color(R.color.md_theme_dark_onSecondaryContainer),
    tertiary = Color(R.color.md_theme_dark_tertiary),
    onTertiary = Color(R.color.md_theme_dark_onTertiary),
    tertiaryContainer = Color(R.color.md_theme_dark_tertiaryContainer),
    onTertiaryContainer = Color(R.color.md_theme_dark_onTertiaryContainer),
    error = Color(R.color.md_theme_dark_error),
    errorContainer = Color(R.color.md_theme_dark_errorContainer),
    onError = Color(R.color.md_theme_dark_onError),
    onErrorContainer = Color(R.color.md_theme_dark_onErrorContainer),
    background = Color(R.color.md_theme_dark_background),
    onBackground = Color(R.color.md_theme_dark_onBackground),
    surface = Color(R.color.md_theme_dark_surface),
    onSurface = Color(R.color.md_theme_dark_onSurface),
    surfaceVariant = Color(R.color.md_theme_dark_surfaceVariant),
    onSurfaceVariant = Color(R.color.md_theme_dark_onSurfaceVariant),
    outline = Color(R.color.md_theme_dark_outline),
    inverseOnSurface = Color(R.color.md_theme_dark_inverseOnSurface),
    inverseSurface = Color(R.color.md_theme_dark_inverseSurface),
    inversePrimary = Color(R.color.md_theme_dark_inversePrimary),
  )

@Composable
fun AppMaterialTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
  val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
  val colorScheme =
    when {
      dynamicColor && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
      dynamicColor && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
      darkTheme -> DarkThemeColors
      else -> LightThemeColors
    }

  MaterialTheme(colorScheme = colorScheme, typography = AppTypography, content = content)
}
