/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package ui.theme

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.window.core.layout.WindowSizeClass
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

@Composable
fun AdaptiveLayout(
    adaptiveLayoutType: MutableState<AdaptiveLayoutType>,
    contentType: MutableState<ContentType>
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    if (windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)) {
        adaptiveLayoutType.value = AdaptiveLayoutType.Expanded
        contentType.value = ContentType.Dual
    } else if (windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)) {
        adaptiveLayoutType.value = AdaptiveLayoutType.Medium
        contentType.value = ContentType.Single
    } else {
        adaptiveLayoutType.value = AdaptiveLayoutType.Compact
        contentType.value = ContentType.Single
    }
}
