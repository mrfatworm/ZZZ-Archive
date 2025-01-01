/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.wengine.components.WEnginesListFilterCard
import feature.wengine.model.WEnginesListState
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding

@Composable
fun WEnginesListScreenDual(
    uiState: WEnginesListState,
    onAction: (WEnginesListAction) -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontalSafePadding()).padding(verticalSafePadding())
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