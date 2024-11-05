/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.wengine.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.wengine.model.WEnginesListState
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding
import utils.AgentSpecialty
import utils.ZzzRarity

@Composable
fun WEnginesListScreenDual(
    uiState: WEnginesListState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onWEngineClick: (Int) -> Unit,
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onSpecialtyChipSelectionChanged: (Set<AgentSpecialty>) -> Unit,
) {
    Row(
        modifier = Modifier.contentPadding(adaptiveLayoutType, AppTheme.dimens),
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