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
import androidx.compose.ui.Modifier
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

/**
 * Ref:
 * [Building an Efficient UI Design System with Jetpack Compose and Compose Multiplatform](https://medium.com/@ahmednasser_12958/building-an-efficient-ui-design-system-0a049b6ee3f7)
 */

private val localColorScheme = compositionLocalOf { ColorScheme() }
private val localTypography = compositionLocalOf { Typography() }
private val localShape = compositionLocalOf { Shape.regular() }
private val localSpacing = compositionLocalOf { Spacing.regular() }
private val localSize = compositionLocalOf { Size() }
private val localAdaptiveLayoutType =
    compositionLocalOf { mutableStateOf(AdaptiveLayoutType.Compact) }
private val localContentType = compositionLocalOf { mutableStateOf(ContentType.Single) }
private val localThemeIsDark = compositionLocalOf { mutableStateOf(true) }
private val localFontScale = compositionLocalOf { mutableStateOf(1f) }
private val localUiScale = compositionLocalOf { mutableStateOf(1f) }

object AppTheme {

    val colors: ColorScheme
        @Composable @ReadOnlyComposable get() = localColorScheme.current

    val typography: Typography
        @Composable @ReadOnlyComposable get() = localTypography.current

    val shape: Shape
        @Composable @ReadOnlyComposable get() = localShape.current

    val spacing: Spacing
        @Composable @ReadOnlyComposable get() = localSpacing.current

    val size: Size
        @Composable @ReadOnlyComposable get() = localSize.current

    val adaptiveLayoutType: AdaptiveLayoutType
        @Composable @ReadOnlyComposable get() = localAdaptiveLayoutType.current.value

    val contentType: ContentType
        @Composable @ReadOnlyComposable get() = localContentType.current.value

    val isDark: MutableState<Boolean>
        @Composable @ReadOnlyComposable get() = localThemeIsDark.current

    val fontScale: MutableState<Float>
        @Composable @ReadOnlyComposable get() = localFontScale.current

    val uiScale: MutableState<Float>
        @Composable @ReadOnlyComposable get() = localUiScale.current
}

@Composable
fun ZzzArchiveTheme(content: @Composable () -> Unit) {
    val adaptiveLayoutType = remember { mutableStateOf(AdaptiveLayoutType.Compact) }
    val contentType = remember { mutableStateOf(ContentType.Single) }
    val isDark = remember { mutableStateOf(true) }
    val colorScheme: ColorScheme = if (isDark.value) darkScheme else lightScheme
    val fontScale = remember { mutableStateOf(1f) }
    val typography = mutableStateOf(provideTypography(fontScale.value))
    val uiScale = remember { mutableStateOf(1f) }
    val fixedSize = mutableStateOf(provideSize(uiScale.value))

    AdaptiveLayout(adaptiveLayoutType, contentType)
    SystemAppearance(!isDark.value)

    CompositionLocalProvider(
        localColorScheme provides colorScheme,
        localTypography provides typography.value,
        localSpacing provides Spacing.regular(),
        localSize provides fixedSize.value,
        localShape provides Shape.regular(),
        localAdaptiveLayoutType provides adaptiveLayoutType,
        localContentType provides contentType,
        localThemeIsDark provides isDark,
        localFontScale provides fontScale,
        localUiScale provides uiScale,
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