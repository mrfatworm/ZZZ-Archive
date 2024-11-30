/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.utils

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.theme.AppTheme
import ui.theme.Dimens

// Parent horizontal padding for Expanded and Medium layout
fun Modifier.containerPadding(adaptiveLayoutType: AdaptiveLayoutType, dimens: Dimens) = this.padding(
    start = when (adaptiveLayoutType) {
        AdaptiveLayoutType.Expanded -> dimens.paddingParentStartExpanded
        AdaptiveLayoutType.Medium -> dimens.paddingParentStartMedium
        AdaptiveLayoutType.Compact -> 0.dp
    }, end = when (adaptiveLayoutType) {
        AdaptiveLayoutType.Expanded -> dimens.paddingParentOthersExpanded
        AdaptiveLayoutType.Medium -> dimens.paddingParentOthersMedium
        AdaptiveLayoutType.Compact -> 0.dp
    }, top = 0.dp, bottom = 0.dp
)

// Padding for each container to match edge-to-edge
@Composable
fun Modifier.contentPadding(): Modifier {
    val topPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val bottomPadding = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
    val dimens = AppTheme.dimens
    return this.padding(
        horizontal = when (AppTheme.adaptiveLayoutType) {
            AdaptiveLayoutType.Expanded -> 0.dp
            AdaptiveLayoutType.Medium -> 0.dp
            AdaptiveLayoutType.Compact -> dimens.paddingParentCompact
        }
    ).padding(
        top = if (topPadding > 0.dp) topPadding else when (AppTheme.adaptiveLayoutType) {
            AdaptiveLayoutType.Expanded -> dimens.paddingParentOthersExpanded
            AdaptiveLayoutType.Medium -> dimens.paddingParentOthersMedium
            AdaptiveLayoutType.Compact -> dimens.paddingParentCompact
        },
        bottom = if (bottomPadding > 0.dp) bottomPadding else when (AppTheme.adaptiveLayoutType) {
            AdaptiveLayoutType.Expanded -> dimens.paddingParentOthersExpanded
            AdaptiveLayoutType.Medium -> dimens.paddingParentOthersMedium
            AdaptiveLayoutType.Compact -> dimens.paddingParentCompact
        }
    )
}