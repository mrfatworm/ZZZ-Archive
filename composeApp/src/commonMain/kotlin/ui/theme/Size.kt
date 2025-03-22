/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Size(
    val s24: Dp = Dp.Unspecified,
    val s36: Dp = Dp.Unspecified,
    val s32: Dp = Dp.Unspecified,
    val s40: Dp = Dp.Unspecified,
    val s48: Dp = Dp.Unspecified,
    val s64: Dp = Dp.Unspecified,
    val s72: Dp = Dp.Unspecified,
    val s100: Dp = Dp.Unspecified,
    val s120: Dp = Dp.Unspecified,
    val s144: Dp = Dp.Unspecified,
    val s180: Dp = Dp.Unspecified,
    val s240: Dp = Dp.Unspecified,
    val s280: Dp = Dp.Unspecified,
    val s320: Dp = Dp.Unspecified,
    val s360: Dp = Dp.Unspecified,
    val s400: Dp = Dp.Unspecified,
    val s512: Dp = Dp.Unspecified,
    val borderWidth: Dp = Dp.Unspecified,
    val maxContainerWidth: Dp = Dp.Unspecified,
    val iconSmall: Dp = Dp.Unspecified,
    val icon: Dp = Dp.Unspecified,
    val iconLarge: Dp = Dp.Unspecified,
    val iconButton: Dp = Dp.Unspecified,
    val iconButtonSmall: Dp = Dp.Unspecified
)

fun provideSize(scale: Float = 1f): Size = Size(
    s24 = 24.dp * scale,
    s32 = 32.dp * scale,
    s36 = 36.dp * scale,
    s40 = 40.dp * scale,
    s48 = 48.dp * scale,
    s64 = 64.dp * scale,
    s72 = 72.dp * scale,
    s100 = 100.dp * scale,
    s120 = 120.dp * scale,
    s144 = 144.dp * scale,
    s180 = 180.dp * scale,
    s240 = 240.dp * scale,
    s280 = 280.dp * scale,
    s320 = 320.dp * scale,
    s360 = 360.dp * scale,
    s400 = 400.dp * scale,
    s512 = 512.dp * scale,
    borderWidth = 3.dp * scale,
    maxContainerWidth = 1440.dp * scale,
    iconSmall = 12.dp * scale,
    icon = 18.dp * scale,
    iconLarge = 24.dp * scale,
    iconButton = 40.dp * scale,
    iconButtonSmall = 32.dp * scale
)