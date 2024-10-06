/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.agent.model.AgentsListState
import ui.component.ContentCard
import ui.component.RarityItem
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding

@Composable
fun AgentsListScreenDual(
    uiState: AgentsListState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onAgentDetailClick: (Int) -> Unit = {}
) {
    Row(
        modifier = Modifier.contentPadding(adaptiveLayoutType, AppTheme.dimens),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        ContentCard(
            modifier = Modifier.weight(1f),
            hasDefaultPadding = false,
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(100.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(AppTheme.dimens.paddingCard),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    count = uiState.agentsList.size,
                    key = { index -> uiState.agentsList[index].id }) { index ->
                    val agent = uiState.agentsList[index]
                    RarityItem(rarityLevel = agent.rarity,
                        name = agent.name,
                        attribute = agent.getAttributeEnum(),
                        imgUrl = agent.getProfileUrl(),
                        onClick = { id ->
                            onAgentDetailClick(id)
                        })
                }
            }
        }
    }


}