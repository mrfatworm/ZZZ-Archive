/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.theme.AppTheme

// Horizontal padding for Container
@Composable
fun horizontalSafePadding() = PaddingValues(
    horizontal = when (AppTheme.adaptiveLayoutType) {
        AdaptiveLayoutType.Expanded -> AppTheme.spacing.s400
        AdaptiveLayoutType.Medium -> AppTheme.spacing.s350
        AdaptiveLayoutType.Compact -> AppTheme.spacing.s300
    }
)

// Vertical edge-to-edge Padding for Container
@Composable
fun verticalSafePadding(): PaddingValues {
    val topPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val bottomPadding = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
    val spacing = AppTheme.spacing
    return PaddingValues(
        top = if (topPadding > 0.dp) topPadding else when (AppTheme.adaptiveLayoutType) {
            AdaptiveLayoutType.Expanded -> spacing.s400
            AdaptiveLayoutType.Medium -> spacing.s350
            AdaptiveLayoutType.Compact -> spacing.s300
        },
        bottom = if (bottomPadding > 0.dp) bottomPadding else when (AppTheme.adaptiveLayoutType) {
            AdaptiveLayoutType.Expanded -> spacing.s400
            AdaptiveLayoutType.Medium -> spacing.s350
            AdaptiveLayoutType.Compact -> spacing.s300
        }
    )
}

// Horizontal gap for dual or more panel layout
@Composable
fun containerGap() = AppTheme.spacing.s400


// Padding for each container inside scaffold
@Composable
fun Modifier.contentPaddingInScaffold(scaffoldPadding: PaddingValues): Modifier {
    val spacing = AppTheme.spacing
    return this.padding(scaffoldPadding).padding(
        when (AppTheme.adaptiveLayoutType) {
            AdaptiveLayoutType.Expanded -> spacing.s400
            AdaptiveLayoutType.Medium -> spacing.s400
            AdaptiveLayoutType.Compact -> spacing.s300
        }
    )
}

// Content vertical gap
@Composable
fun contentGap() = when (AppTheme.adaptiveLayoutType) {
    AdaptiveLayoutType.Expanded -> AppTheme.spacing.s350
    AdaptiveLayoutType.Medium -> AppTheme.spacing.s300
    AdaptiveLayoutType.Compact -> AppTheme.spacing.s300
}

@Composable
fun cardPadding() = AppTheme.spacing.s400

@Composable
fun cardPaddingWithHeader() = PaddingValues(
    start = cardPadding(), top = 0.dp, end = cardPadding(), bottom = cardPadding()
)

@Composable
fun rowListGap() = Arrangement.spacedBy(AppTheme.spacing.s350)

@Composable
fun gridListHorizontalGap() = Arrangement.spacedBy(AppTheme.spacing.s350)

@Composable
fun gridListVerticalGap() = Arrangement.spacedBy(AppTheme.spacing.s400)