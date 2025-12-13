/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import feature.agent.model.AgentsListState
import feature.agent.model.stubAgentsList
import ui.components.dialogs.AgentMaterialDialog
import ui.components.items.HighlightAgentListItem
import ui.components.items.RarityItem
import ui.theme.AppTheme
import ui.utils.gridListHorizontalGap
import ui.utils.gridListVerticalGap
import ui.utils.verticalSafePadding

@Composable
fun AgentsList(
    modifier: Modifier,
    uiState: AgentsListState,
    lazyGridState: LazyGridState = rememberLazyGridState(),
    onGalleryClick: (Int) -> Unit
) {
    var isShowMaterialDialog by remember { mutableStateOf(false) }
    var selectedAgent by remember { mutableStateOf(stubAgentsList[0]) }
    var lastClickedAgentId by remember { mutableStateOf<Int?>(null) }
    LazyVerticalGrid(
        state = lazyGridState,
        columns = GridCells.Adaptive(AppTheme.size.s100),
        modifier = modifier,
        contentPadding = verticalSafePadding(),
        horizontalArrangement = gridListHorizontalGap(),
        verticalArrangement = gridListVerticalGap()
    ) {
        item(span = { GridItemSpan(this.maxLineSpan) }) {
            BoxWithConstraints {
                val isDualItem = maxWidth - (AppTheme.spacing.s300) >= AppTheme.size.s280 * 2
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                    maxItemsInEachRow = 2
                ) {
                    uiState.highlightAgentsList.forEach { agent ->
                        HighlightAgentListItem(
                            modifier = Modifier.weight(1f),
                            uiState = agent,
                            isSelected = agent.id == lastClickedAgentId,
                            onHover = { isHovered ->
                                if (isHovered && agent.id != lastClickedAgentId) {
                                    lastClickedAgentId = null
                                }
                            },
                            onClick = {
                                selectedAgent = agent
                                lastClickedAgentId = agent.id
                                isShowMaterialDialog = true
                            }
                        )
                    }
                    if (isDualItem && uiState.highlightAgentsList.size % 2 == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
        items(
            count = uiState.filteredAgentsList.size,
            key = { index -> uiState.filteredAgentsList[index].id }
        ) { index ->
            val agent = uiState.filteredAgentsList[index]
            RarityItem(
                modifier = Modifier.animateItem(),
                rarity = agent.rarity,
                attribute = agent.attribute,
                imgUrl = agent.imageUrl,
                isSelected = agent.id == lastClickedAgentId,
                onHover = { isHovered ->
                    if (isHovered && agent.id != lastClickedAgentId) {
                        lastClickedAgentId = null
                    }
                },
                onClick = {
                    selectedAgent = agent
                    lastClickedAgentId = agent.id
                    isShowMaterialDialog = true
                }
            )
        }
        item(span = { GridItemSpan(this.maxLineSpan) }) {
            Spacer(modifier = Modifier.size(AppTheme.size.s64))
        }
    }
    if (isShowMaterialDialog) {
        AgentMaterialDialog(
            materialUrl = selectedAgent.materialUrl,
            weeklyMaterialUrl = selectedAgent.weeklyMaterialUrl,
            skillMaterialUrls = selectedAgent.skillMaterialUrls,
            levelMaterialUrls = selectedAgent.levelMaterialUrls,
            wEngineMaterialUrls = selectedAgent.wEngineMaterialUrls,
            onGalleryClick = {
                isShowMaterialDialog = false
                onGalleryClick(selectedAgent.id)
            },
            onDismiss = { isShowMaterialDialog = false }
        )
    }
}
