/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.bangboo.components.BangbooListFilterCard
import feature.bangboo.model.BangbooListState
import ui.utils.containerGap
import ui.utils.contentPadding

@Composable
fun BangbooListScreenDual(
    uiState: BangbooListState,
    onAction: (BangbooListAction) -> Unit
) {
    Row(
        modifier = Modifier.contentPadding(),
        horizontalArrangement = Arrangement.spacedBy(containerGap())
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