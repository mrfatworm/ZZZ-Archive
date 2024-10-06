/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.agent.model.AgentsListState
import org.jetbrains.compose.resources.stringResource
import ui.component.ContentCard
import ui.component.RarityItem
import ui.component.ZzzTopBar
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agents

@Composable
fun AgentsListScreenSingle(
    uiState: AgentsListState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onAgentDetailClick: (Int) -> Unit = {},
    onBackClick: () -> Unit
) {
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        if (adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            ZzzTopBar(title = stringResource(Res.string.agents), onBackClick = onBackClick)
        }
    }) { contentPadding ->
        ContentCard(
            modifier = Modifier.padding(contentPadding).padding(
                start = AppTheme.dimens.paddingParentCompact,
                end = AppTheme.dimens.paddingParentCompact,
                top = AppTheme.dimens.paddingParentCompact
            ),
            hasDefaultPadding = false,
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(100.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(AppTheme.dimens.paddingCard),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(count = uiState.agentsList.size,
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