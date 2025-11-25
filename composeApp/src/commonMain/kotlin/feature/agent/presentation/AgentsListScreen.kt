/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.agent.components.AgentFilterBottomSheet
import feature.agent.model.AgentsListState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel
import ui.theme.AppTheme
import ui.utils.ContentType
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.clear
import zzzarchive.composeapp.generated.resources.filter
import zzzarchive.composeapp.generated.resources.ic_close
import zzzarchive.composeapp.generated.resources.ic_filter
import zzzarchive.composeapp.generated.resources.ic_filter_filled

@Composable
fun AgentsListScreen(
    onAgentClick: (Int) -> Unit,
    onGalleryClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel: AgentsListViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    AgentsListContent(
        uiState = uiState,
        onAction = { action ->
            when (action) {
                is AgentsListAction.ClickAgent -> {
                    onAgentClick(action.agentId)
                }

                is AgentsListAction.ClickGallery -> {
                    onGalleryClick(action.agentId)
                }

                AgentsListAction.ClickBack -> onBackClick()

                else -> viewModel.onAction(action)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentsListContent(
    uiState: AgentsListState,
    onAction: (AgentsListAction) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val isFiltered =
        uiState.selectedRarity.isNotEmpty() || uiState.selectedAttributes.isNotEmpty() ||
            uiState.selectedSpecialties.isNotEmpty() ||
            uiState.selectedFactionId != 0
    Scaffold(
        containerColor = AppTheme.colors.surface,
        floatingActionButtonPosition = if (AppTheme.contentType ==
            ContentType.Single
        ) {
            FabPosition.End
        } else {
            FabPosition.Start
        },
        floatingActionButton = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (isFiltered) {
                    FloatingActionButton(
                        containerColor = AppTheme.colors.surfaceContainer,
                        contentColor = AppTheme.colors.primary,
                        onClick = {
                            onAction(AgentsListAction.ClearFilter)
                        }
                    ) {
                        Icon(
                            imageVector = vectorResource(Res.drawable.ic_close),
                            contentDescription = stringResource(Res.string.clear)
                        )
                    }
                }

                FloatingActionButton(
                    containerColor = AppTheme.colors.primary,
                    contentColor = AppTheme.colors.onPrimary,
                    onClick = {
                        showBottomSheet = true
                    }
                ) {
                    Icon(
                        imageVector = vectorResource(
                            if (isFiltered) Res.drawable.ic_filter_filled else Res.drawable.ic_filter
                        ),
                        contentDescription = stringResource(Res.string.filter)
                    )
                }
            }
        }
    ) { scaffoldPadding ->
        if (AppTheme.contentType == ContentType.Single) {
            AgentsListScreenSingle(uiState = uiState, onAction)
        } else {
            AgentsListScreenDual(uiState = uiState, onAction)
        }
    }
    if (showBottomSheet) {
        AgentFilterBottomSheet(
            sheetState = sheetState,
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
            onDismiss = { showBottomSheet = false }
        )
    }
}
