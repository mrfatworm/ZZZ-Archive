/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.chips

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.theme.AppTheme
import ui.utils.cardPadding
import utils.ZzzRarity
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_rare

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RarityFilterChipsList(
    selectedRarity: Set<ZzzRarity>, onSelectionChanged: (Set<ZzzRarity>) -> Unit
) {
    val rarities = listOf(ZzzRarity.RARITY_S, ZzzRarity.RARITY_A)
    FlowRow(
        modifier = Modifier.fillMaxWidth().padding(horizontal = cardPadding()),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
    ) {
        rarities.forEach { rarity ->
            ZzzFilterChip(text = rarity.code,
                iconRes = Res.drawable.ic_rare,
                selected = selectedRarity.contains(rarity),
                onClick = {
                    val newSelection = if (selectedRarity.contains(rarity)) {
                        selectedRarity - rarity
                    } else {
                        selectedRarity + rarity
                    }
                    onSelectionChanged(newSelection)
                })
        }
    }
}