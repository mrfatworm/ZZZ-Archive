/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: Apache-2.0
 */

package utils

import androidx.compose.ui.graphics.Brush
import ui.utils.rarityFiveBrush
import ui.utils.rarityFourBrush
import ui.utils.rarityOneBrush
import ui.utils.rarityThreeBrush
import ui.utils.rarityTwoBrush


enum class ZzzArchiveRarity(val rarity: Int, val color: Brush) {
    One(1, rarityOneBrush),
    Two(2, rarityTwoBrush),
    Three(3, rarityThreeBrush),
    Four(4, rarityFourBrush),
    Five(5, rarityFiveBrush)
}