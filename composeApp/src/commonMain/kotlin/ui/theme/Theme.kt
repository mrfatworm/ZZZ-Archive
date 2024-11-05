/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier

/**
 * Ref:
 * [Building an Efficient UI Design System with Jetpack Compose and Compose Multiplatform](https://medium.com/@ahmednasser_12958/building-an-efficient-ui-design-system-0a049b6ee3f7)
 */

private val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }
private val localColorScheme = staticCompositionLocalOf { ColorScheme() }
private val localTypography = staticCompositionLocalOf { Typography() }
private val localRadius = staticCompositionLocalOf { Radius() }
private val localDimens = staticCompositionLocalOf { Dimens() }

object AppTheme {
    val isDark: MutableState<Boolean>
        @Composable @ReadOnlyComposable get() = LocalThemeIsDark.current

    val colors: ColorScheme
        @Composable @ReadOnlyComposable get() = localColorScheme.current

    val typography: Typography
        @Composable @ReadOnlyComposable get() = localTypography.current

    val radius: Radius
        @Composable @ReadOnlyComposable get() = localRadius.current

    val dimens: Dimens
        @Composable @ReadOnlyComposable get() = localDimens.current
}

@Composable
fun ZzzArchiveTheme(
    isDarkTheme: Boolean = true, content: @Composable () -> Unit
) {
    val isDarkState = remember { mutableStateOf(isDarkTheme) }
    val colorScheme: ColorScheme = if (isDarkState.value) darkScheme else lightScheme
    val typography: Typography = provideTypography()
    SystemAppearance(!isDarkState.value)

    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkState,
        localColorScheme provides colorScheme,
        localTypography provides typography,
        localDimens provides Dimens(),
        localRadius provides Radius(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = AppTheme.colors.surface)
        ) {
            content()
        }
    }
}


@Composable
internal expect fun SystemAppearance(isDark: Boolean)