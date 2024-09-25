/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
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
import ui.theme.Dimens

// Parent horizontal padding for Expanded and Medium layout
fun Modifier.containerPadding(navigationType: NavigationType, dimens: Dimens) = this.padding(
    start = when (navigationType) {
        NavigationType.NAVIGATION_DRAWER -> dimens.paddingParentStartExpanded
        NavigationType.NAVIGATION_RAIL -> dimens.paddingParentStartMedium
        NavigationType.BOTTOM_NAVIGATION -> 0.dp
    }, end = when (navigationType) {
        NavigationType.NAVIGATION_DRAWER -> dimens.paddingParentOthersExpanded
        NavigationType.NAVIGATION_RAIL -> dimens.paddingParentOthersMedium
        NavigationType.BOTTOM_NAVIGATION -> 0.dp
    }, top = 0.dp, bottom = 0.dp
)

// Padding for each container to match edge-to-edge
@Composable
fun Modifier.contentPadding(navigationType: NavigationType, dimens: Dimens): Modifier {
    val topPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val bottomPadding = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
    return this.padding(
        horizontal = when (navigationType) {
            NavigationType.NAVIGATION_DRAWER -> 0.dp
            NavigationType.NAVIGATION_RAIL -> 0.dp
            NavigationType.BOTTOM_NAVIGATION -> dimens.paddingParentCompact
        }
    ).padding(
        top = if (topPadding > 0.dp) topPadding else when (navigationType) {
            NavigationType.NAVIGATION_DRAWER -> dimens.paddingParentOthersExpanded
            NavigationType.NAVIGATION_RAIL -> dimens.paddingParentOthersMedium
            NavigationType.BOTTOM_NAVIGATION -> dimens.paddingParentCompact
        }, bottom = if (bottomPadding > 0.dp) bottomPadding else when (navigationType) {
            NavigationType.NAVIGATION_DRAWER -> dimens.paddingParentOthersExpanded
            NavigationType.NAVIGATION_RAIL -> dimens.paddingParentOthersMedium
            NavigationType.BOTTOM_NAVIGATION -> dimens.paddingParentCompact
        }
    )
}