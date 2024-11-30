/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.bangboo.model.BangbooListState
import ui.theme.AppTheme
import ui.utils.contentPadding
import utils.AgentAttribute
import utils.ZzzRarity

@Composable
fun BangbooListScreenDual(
    uiState: BangbooListState,
    onBangbooClick: (Int) -> Unit,
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onAttributeChipSelectionChanged: (Set<AgentAttribute>) -> Unit
) {
    Row(
        modifier = Modifier.contentPadding(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        BangbooListFilterCard(
            uiState = uiState,
            onBangbooClick = onBangbooClick,
            onRarityChipSelectionChanged = onRarityChipSelectionChanged,
            onAttributeChipSelectionChanged = onAttributeChipSelectionChanged
        )
    }
}