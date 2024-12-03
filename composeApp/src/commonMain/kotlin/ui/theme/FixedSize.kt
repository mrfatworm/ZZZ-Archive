/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class FixedSize(
    val maxContainerWidth: Dp = Dp.Unspecified,
    val rarityItemMediumSize: Dp = Dp.Unspecified,
    val rarityItemSmallSize: Dp = Dp.Unspecified,
    val galleryItemSize: Dp = Dp.Unspecified,
    val maxDialogWidth: Dp = Dp.Unspecified,
    val maxDialogHeight: Dp = Dp.Unspecified,
    val iconButtonSize: Dp = Dp.Unspecified,
)

fun provideFixedSize(scale: Float = 1f): FixedSize = FixedSize(
    maxContainerWidth = 1440.dp * scale,
    rarityItemMediumSize = 100.dp * scale,
    rarityItemSmallSize = 60.dp * scale,
    galleryItemSize = 120.dp * scale,
    maxDialogWidth = 320.dp * scale,
    maxDialogHeight = 512.dp * scale,
    iconButtonSize = 40.dp * scale,
)