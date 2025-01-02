/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Size(
    val maxContainerWidth: Dp = Dp.Unspecified,
    val rarityItemMediumSize: Dp = Dp.Unspecified,
    val rarityItemSmallSize: Dp = Dp.Unspecified,
    val forumImageSize: Dp = Dp.Unspecified,
    val galleryItemSize: Dp = Dp.Unspecified,
    val maxDialogWidth: Dp = Dp.Unspecified,
    val minDialogWidth: Dp = Dp.Unspecified,
    val maxDialogHeight: Dp = Dp.Unspecified,
    val iconButtonSize: Dp = Dp.Unspecified,
    val smallIconButtonSize: Dp = Dp.Unspecified,
    val smallIconSize: Dp = Dp.Unspecified,
    val iconSize: Dp = Dp.Unspecified,
    val largeIconSize: Dp = Dp.Unspecified,
    val extraLargeIconSize: Dp = Dp.Unspecified,
    val borderWidth: Dp = Dp.Unspecified
)

fun provideSize(scale: Float = 1f): Size = Size(
    maxContainerWidth = 1440.dp * scale,
    rarityItemMediumSize = 100.dp * scale,
    rarityItemSmallSize = 64.dp * scale,
    forumImageSize = 72.dp * scale,
    galleryItemSize = 144.dp * scale,
    maxDialogWidth = 320.dp * scale,
    minDialogWidth = 240.dp * scale,
    maxDialogHeight = 512.dp * scale,
    iconButtonSize = 40.dp,
    smallIconButtonSize = 32.dp,
    smallIconSize = 12.dp,
    iconSize = 18.dp,
    largeIconSize = 24.dp,
    extraLargeIconSize = 48.dp,
    borderWidth = 3.dp
)