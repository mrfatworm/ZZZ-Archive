/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import feature.agent.components.AgentFilterBottomSheet
import feature.agent.components.AgentsListFilterCard
import feature.agent.model.AgentsListState
import org.jetbrains.compose.resources.stringResource
import ui.components.TopBarScaffold
import ui.components.buttons.ZzzIconButton
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPaddingInScaffold
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agents
import zzzarchive.composeapp.generated.resources.filter
import zzzarchive.composeapp.generated.resources.ic_filter
import zzzarchive.composeapp.generated.resources.ic_filter_filled

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentsListScreenSingle(
    uiState: AgentsListState,
    onAction: (AgentsListAction) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val isFiltered =
        uiState.selectedRarity.isNotEmpty() || uiState.selectedAttributes.isNotEmpty() || uiState.selectedSpecialties.isNotEmpty() || uiState.selectedFactionId != 0
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        AnimatedVisibility(AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            TopBarScaffold(
                title = stringResource(Res.string.agents),
                onBackClick = {
                    onAction(AgentsListAction.ClickBack)
                },
                actions = {
                    ZzzIconButton(iconRes = if (isFiltered) Res.drawable.ic_filter_filled else Res.drawable.ic_filter,
                        contentDescriptionRes = Res.string.filter,
                        tint = if (isFiltered) AppTheme.colors.primary else AppTheme.colors.onSurface,
                        onClick = {
                            showBottomSheet = true
                        })
                })
        }
    }) { contentPadding ->
        Column(
            modifier = Modifier.contentPaddingInScaffold(contentPadding)
        ) {
            AgentsListFilterCard(
                modifier = Modifier.weight(1f),
                uiState = uiState,
                invisibleFilter = AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact,
                onAgentClick = {
                    onAction(AgentsListAction.ClickAgent(it))
                },
                onRarityChipSelectionChanged = {
                    onAction(AgentsListAction.ChangeRarityFilter(it))
                },
                onAttributeChipSelectionChanged = {
                    onAction(AgentsListAction.ChangeAttributeFilter(it))
                },
                onSpecialtyChipSelectionChanged = {
                    onAction(AgentsListAction.ChangeSpecialtyFilter(it))
                }
            )
        }
        if (showBottomSheet) {
            AgentFilterBottomSheet(sheetState = sheetState,
                uiState = uiState,
                onRarityChipSelectionChanged = {
                    onAction(AgentsListAction.ChangeRarityFilter(it))
                },
                onAttributeChipSelectionChanged = {
                    onAction(AgentsListAction.ChangeAttributeFilter(it))
                },
                onSpecialtyChipSelectionChanged = {
                    onAction(AgentsListAction.ChangeSpecialtyFilter(it))
                },
                onFactionChipSelectionChanged = {
                    onAction(AgentsListAction.ChangeFactionFilter(it))
                },
                onDismiss = { showBottomSheet = false })
        }
    }
}