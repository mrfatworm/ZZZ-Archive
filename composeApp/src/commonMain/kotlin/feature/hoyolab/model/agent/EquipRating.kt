/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.agent

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import ui.theme.AppTheme

data class EquipRating(val symbol: String, val color: Color)

@Composable
fun getEquipRatingState(equipRating: String?): EquipRating? = when (equipRating) {
    "ER_SS" -> EquipRating("SS", AppTheme.colors.rarityS)
    "ER_S_Plus" -> EquipRating("S+", AppTheme.colors.rarityS)
    "ER_S" -> EquipRating("S", AppTheme.colors.rarityS)
    "ER_A" -> EquipRating("A", AppTheme.colors.rarityA)
    "ER_B" -> EquipRating("B", AppTheme.colors.rarityB)
    "ER_C" -> EquipRating("C", AppTheme.colors.rarityC)
    "ER_Default", null -> null
    else -> null
}
