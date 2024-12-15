/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation

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
import feature.wengine.components.WEngineFilterBottomSheet
import feature.wengine.components.WEnginesListFilterCard
import feature.wengine.model.WEnginesListState
import org.jetbrains.compose.resources.stringResource
import ui.components.TopBarScaffold
import ui.components.buttons.ZzzIconButton
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPaddingInScaffold
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.filter
import zzzarchive.composeapp.generated.resources.ic_filter
import zzzarchive.composeapp.generated.resources.ic_filter_filled
import zzzarchive.composeapp.generated.resources.w_engines

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WEnginesListScreenSingle(
    uiState: WEnginesListState, onAction: (WEnginesListAction) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val isFiltered = uiState.selectedRarity.isNotEmpty() || uiState.selectedSpecialties.isNotEmpty()
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        AnimatedVisibility(AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            TopBarScaffold(title = stringResource(Res.string.w_engines), onBackClick = {
                onAction(WEnginesListAction.ClickBack)
            }, actions = {
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
            WEnginesListFilterCard(modifier = Modifier.weight(1f),
                uiState = uiState,
                invisibleFilter = AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact,
                onWEngineClick = {
                    onAction(WEnginesListAction.ClickWEngine(it))
                },
                onRarityChipSelectionChanged = {
                    onAction(WEnginesListAction.ChangeRarityFilter(it))
                },
                onSpecialtyChipSelectionChanged = {
                    onAction(WEnginesListAction.ChangeSpecialtyFilter(it))
                })
        }
        if (showBottomSheet) {
            WEngineFilterBottomSheet(sheetState = sheetState,
                uiState = uiState,
                onRarityChipSelectionChanged = {
                    onAction(WEnginesListAction.ChangeRarityFilter(it))
                },
                onSpecialtyChipSelectionChanged = {
                    onAction(WEnginesListAction.ChangeSpecialtyFilter(it))
                },
                onDismiss = { showBottomSheet = false })
        }
    }
}