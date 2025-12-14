/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType
import ui.utils.SnackbarController
import ui.utils.rememberSafeUriHandler
import ui.utils.rememberSnackbarController

/**
 * Ref:
 * [Building an Efficient UI Design System with Jetpack Compose and Compose Multiplatform](https://medium.com/@ahmednasser_12958/building-an-efficient-ui-design-system-0a049b6ee3f7)
 */

@Stable
class ThemeController(initialIsDark: Boolean = true, initialFontScale: Float = 1.0f, initialUiScale: Float = 1.0f) {
    val isDark = mutableStateOf(initialIsDark)
    val fontScale = mutableStateOf(initialFontScale)
    val uiScale = mutableStateOf(initialUiScale)

    fun setTheme(isDarkTheme: Boolean) {
        isDark.value = isDarkTheme
    }

    fun setFontScale(scale: Float) {
        fontScale.value = scale.coerceIn(0.5f, 2.0f)
    }

    fun setUiScale(scale: Float) {
        uiScale.value = scale.coerceIn(0.5f, 2.0f)
    }
}

private val LocalColorScheme = staticCompositionLocalOf<ColorScheme> {
    error("No ColorScheme provided")
}
private val LocalTypography = staticCompositionLocalOf<Typography> {
    error("No Typography provided")
}
private val LocalShape = staticCompositionLocalOf<Shape> {
    error("No Shape provided")
}
private val LocalSpacing = staticCompositionLocalOf<Spacing> {
    error("No Spacing provided")
}
private val LocalSize = staticCompositionLocalOf<Size> {
    error("No Size provided")
}
private val LocalAdaptiveLayoutType = staticCompositionLocalOf<AdaptiveLayoutType> {
    error("No AdaptiveLayoutType provided")
}
private val LocalContentType = staticCompositionLocalOf<ContentType> {
    error("No ContentType provided")
}
private val LocalThemeController = staticCompositionLocalOf<ThemeController> {
    error("No ThemeController provided")
}
val LocalSnackbarController = staticCompositionLocalOf<SnackbarController> {
    error("No SnackbarController provided")
}

@Stable
object AppTheme {
    val colors: ColorScheme
        @Composable @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: Typography
        @Composable @ReadOnlyComposable
        get() = LocalTypography.current

    val shape: Shape
        @Composable @ReadOnlyComposable
        get() = LocalShape.current

    val spacing: Spacing
        @Composable @ReadOnlyComposable
        get() = LocalSpacing.current

    val size: Size
        @Composable @ReadOnlyComposable
        get() = LocalSize.current

    val adaptiveLayoutType: AdaptiveLayoutType
        @Composable @ReadOnlyComposable
        get() = LocalAdaptiveLayoutType.current

    val contentType: ContentType
        @Composable @ReadOnlyComposable
        get() = LocalContentType.current

    val themeController: ThemeController
        @Composable @ReadOnlyComposable
        get() = LocalThemeController.current

    val isDark: Boolean
        @Composable @ReadOnlyComposable
        get() = themeController.isDark.value

    val fontScale: Float
        @Composable @ReadOnlyComposable
        get() = themeController.fontScale.value

    val uiScale: Float
        @Composable @ReadOnlyComposable
        get() = themeController.uiScale.value
}

@Composable
fun ZzzArchiveTheme(content: @Composable () -> Unit) {
    val adaptiveLayoutType = remember { mutableStateOf(AdaptiveLayoutType.Compact) }
    val contentType = remember { mutableStateOf(ContentType.Single) }

    val themeController = remember {
        ThemeController(
            initialIsDark = true,
            initialFontScale = 1.0f,
            initialUiScale = 1.0f
        )
    }

    val snackbarController = rememberSnackbarController()
    val safeUriHandler = rememberSafeUriHandler(snackbarController = snackbarController)

    val colorScheme: ColorScheme = if (themeController.isDark.value) darkScheme else lightScheme
    val typography = provideTypography(themeController.fontScale.value)
    val size = provideSize(themeController.uiScale.value)

    AdaptiveLayout(adaptiveLayoutType, contentType)
    SystemAppearance(!themeController.isDark.value)

    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides typography,
        LocalSpacing provides Spacing.regular(),
        LocalSize provides size,
        LocalShape provides Shape.regular(),
        LocalAdaptiveLayoutType provides adaptiveLayoutType.value,
        LocalContentType provides contentType.value,
        LocalThemeController provides themeController,
        LocalSnackbarController provides snackbarController,
        LocalUriHandler provides safeUriHandler
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(color = AppTheme.colors.surface)
        ) {
            content()
            SnackbarHost(
                hostState = snackbarController.snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .navigationBarsPadding()
                    .imePadding()
            )
        }
    }
}

@Composable
internal expect fun SystemAppearance(isDark: Boolean)
