/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.bangboo.components.BangbooListFilterCard
import feature.bangboo.model.BangbooListState
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding

@Composable
fun BangbooListScreenDual(
    uiState: BangbooListState,
    onAction: (BangbooListAction) -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontalSafePadding()).padding(verticalSafePadding())
    ) {
        BangbooListFilterCard(
            uiState = uiState,
            onBangbooClick = {
                onAction(BangbooListAction.ClickBangboo(it))
            },
            onRarityChipSelectionChanged = {
                onAction(BangbooListAction.ChangeRarityFilter(it))
            },
            onAttributeChipSelectionChanged = {
                onAction(BangbooListAction.ChangeAttributeFilter(it))
            }
        )
    }
}