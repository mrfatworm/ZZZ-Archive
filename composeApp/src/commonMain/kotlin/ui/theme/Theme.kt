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
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

/**
 * Ref:
 * [Building an Efficient UI Design System with Jetpack Compose and Compose Multiplatform](https://medium.com/@ahmednasser_12958/building-an-efficient-ui-design-system-0a049b6ee3f7)
 */

private val localColorScheme = staticCompositionLocalOf { ColorScheme() }
private val localTypography = staticCompositionLocalOf { Typography() }
private val localRadius = staticCompositionLocalOf { Radius() }
private val localDimens = staticCompositionLocalOf { Dimens() }
private val localAdaptiveLayoutType =
    compositionLocalOf { mutableStateOf(AdaptiveLayoutType.Compact) }
private val localContentType = compositionLocalOf { mutableStateOf(ContentType.Single) }
private val localThemeIsDark = compositionLocalOf { mutableStateOf(true) }

object AppTheme {

    val colors: ColorScheme
        @Composable @ReadOnlyComposable get() = localColorScheme.current

    val typography: Typography
        @Composable @ReadOnlyComposable get() = localTypography.current

    val radius: Radius
        @Composable @ReadOnlyComposable get() = localRadius.current

    val dimens: Dimens
        @Composable @ReadOnlyComposable get() = localDimens.current

    val adaptiveLayoutType: AdaptiveLayoutType
        @Composable @ReadOnlyComposable get() = localAdaptiveLayoutType.current.value

    val contentType: ContentType
        @Composable @ReadOnlyComposable get() = localContentType.current.value

    val isDark: MutableState<Boolean>
        @Composable @ReadOnlyComposable get() = localThemeIsDark.current
}

@Composable
fun ZzzArchiveTheme(content: @Composable () -> Unit) {
    val adaptiveLayoutType = remember { mutableStateOf(AdaptiveLayoutType.Compact) }
    val contentType = remember { mutableStateOf(ContentType.Single) }
    val isDarkState = remember { mutableStateOf(true) }
    val colorScheme: ColorScheme = if (isDarkState.value) darkScheme else lightScheme
    val typography: Typography = provideTypography()

    AdaptiveLayout(adaptiveLayoutType, contentType)
    SystemAppearance(!isDarkState.value)

    CompositionLocalProvider(
        localColorScheme provides colorScheme,
        localTypography provides typography,
        localDimens provides Dimens(),
        localRadius provides Radius(),
        localThemeIsDark provides isDarkState,
        localAdaptiveLayoutType provides adaptiveLayoutType,
        localContentType provides contentType
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