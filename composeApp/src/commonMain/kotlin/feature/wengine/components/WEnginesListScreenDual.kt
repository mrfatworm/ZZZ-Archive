/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.wengine.model.WEnginesListState
import ui.theme.AppTheme
import ui.utils.contentPadding
import utils.AgentSpecialty
import utils.ZzzRarity

@Composable
fun WEnginesListScreenDual(
    uiState: WEnginesListState,
    onWEngineClick: (Int) -> Unit,
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onSpecialtyChipSelectionChanged: (Set<AgentSpecialty>) -> Unit
) {
    Row(
        modifier = Modifier.contentPadding(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        WEnginesListFilterCard(
            uiState = uiState,
            onWEngineClick = onWEngineClick,
            onRarityChipSelectionChanged = onRarityChipSelectionChanged,
            onSpecialtyChipSelectionChanged = onSpecialtyChipSelectionChanged
        )
    }
}