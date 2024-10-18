/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import app.agent.model.AgentsListState
import org.jetbrains.compose.resources.stringResource
import ui.component.ZzzIconButton
import ui.component.ZzzTopBar
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agents
import zzzarchive.composeapp.generated.resources.filter
import zzzarchive.composeapp.generated.resources.ic_filter
import zzzarchive.composeapp.generated.resources.ic_filter_filled

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentsListScreenSingle(
    uiState: AgentsListState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onAgentClick: (Int) -> Unit = {},
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onAttributeChipSelectionChanged: (Set<AgentAttribute>) -> Unit,
    onSpecialtyChipSelectionChanged: (Set<AgentSpecialty>) -> Unit,
    onFactionChipSelectionChanged: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val isFiltered =
        uiState.selectedRarity.isNotEmpty() || uiState.selectedAttributes.isNotEmpty() || uiState.selectedSpecialties.isNotEmpty() || uiState.selectedFactionId != 0
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        AnimatedVisibility(adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            ZzzTopBar(title = stringResource(Res.string.agents),
                onBackClick = onBackClick,
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
            modifier = Modifier.padding(contentPadding)
                .padding(AppTheme.dimens.paddingParentCompact)
        ) {
            AgentsListFilterCard(
                modifier = Modifier.weight(1f),
                uiState = uiState,
                invisibleFilter = adaptiveLayoutType == AdaptiveLayoutType.Compact,
                onAgentClick = onAgentClick,
                onRarityChipSelectionChanged = onRarityChipSelectionChanged,
                onAttributeChipSelectionChanged = onAttributeChipSelectionChanged,
                onSpecialtyChipSelectionChanged = onSpecialtyChipSelectionChanged
            )
        }
        if (showBottomSheet) {
            AgentFilterBottomSheet(sheetState = sheetState,
                uiState = uiState,
                onRarityChipSelectionChanged = onRarityChipSelectionChanged,
                onAttributeChipSelectionChanged = onAttributeChipSelectionChanged,
                onSpecialtyChipSelectionChanged = onSpecialtyChipSelectionChanged,
                onFactionChipSelectionChanged = onFactionChipSelectionChanged,
                onDismiss = { showBottomSheet = false })
        }
    }
}