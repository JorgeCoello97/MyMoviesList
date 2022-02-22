package com.jcoello.mymovieslistv2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkColorPalette = darkColorScheme(
    primary = Color.Black,
    primaryContainer = Color.Black,
    secondary = ColorAccent,
    secondaryContainer = Color.Black,
    onSecondaryContainer = Color.White,
    background = ColorBackground,
    surface = Color.Black,
    surfaceVariant = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color.White,
    error = Color.Red,
)

@Composable
fun MyMoviesListV2Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorPalette,
        typography = materialTypography,
        content = content
    )
}