/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val paddingParentStartExpanded: Dp = 8.dp,
    val paddingParentOthersExpanded: Dp = 16.dp,
    val gapParentExpanded: Dp = 16.dp,
    val gapContentExpanded: Dp = 12.dp,
    val paddingParentStartMedium: Dp = 8.dp,
    val paddingParentOthersMedium: Dp = 16.dp,
    val gapParentMedium: Dp = 12.dp,
    val gapContentMedium: Dp = 12.dp,
    val paddingParentCompact: Dp = 8.dp,
    val gapParentCompact: Dp = 8.dp,
    val gapContentCompact: Dp = 8.dp,
    val paddingUnderCardHeader: Dp = 0.dp,
    val paddingCard: Dp = 16.dp,
    val gapImageProfileList: Dp = 12.dp,
    val maxContainerWidth: Dp = 1280.dp,
    val borderWidth: Dp = 3.dp
)