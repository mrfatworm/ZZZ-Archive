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
import feature.wengine.presentation.WEnginesListAction
import ui.theme.AppTheme
import ui.utils.contentPadding

@Composable
fun WEnginesListScreenDual(
    uiState: WEnginesListState,
    onAction: (WEnginesListAction) -> Unit
) {
    Row(
        modifier = Modifier.contentPadding(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        WEnginesListFilterCard(
            uiState = uiState,
            onWEngineClick = {
                onAction(WEnginesListAction.ClickWEngine(it))
            },
            onRarityChipSelectionChanged = {
                onAction(WEnginesListAction.ChangeRarityFilter(it))
            },
            onSpecialtyChipSelectionChanged = {
                onAction(WEnginesListAction.ChangeSpecialtyFilter(it))
            }
        )
    }
}