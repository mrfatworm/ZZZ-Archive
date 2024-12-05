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
import feature.bangboo.presentation.BangbooListAction
import ui.theme.AppTheme
import ui.utils.contentPadding

@Composable
fun BangbooListScreenDual(
    uiState: BangbooListState,
    onAction: (BangbooListAction) -> Unit
) {
    Row(
        modifier = Modifier.contentPadding(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
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