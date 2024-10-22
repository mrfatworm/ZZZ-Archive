/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine.compose

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
import app.wengine.model.WEnginesListState
import org.jetbrains.compose.resources.stringResource
import ui.component.ZzzIconButton
import ui.component.ZzzTopBar
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import utils.AgentSpecialty
import utils.ZzzRarity
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.filter
import zzzarchive.composeapp.generated.resources.ic_filter
import zzzarchive.composeapp.generated.resources.ic_filter_filled
import zzzarchive.composeapp.generated.resources.w_engines

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WEnginesListScreenSingle(
    uiState: WEnginesListState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onWEngineClick: (Int) -> Unit = {},
    onRarityChipSelectionChanged: (Set<ZzzRarity>) -> Unit,
    onSpecialtyChipSelectionChanged: (Set<AgentSpecialty>) -> Unit,
    onBackClick: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val isFiltered =
        uiState.selectedRarity.isNotEmpty() || uiState.selectedSpecialties.isNotEmpty()
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        AnimatedVisibility(adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            ZzzTopBar(title = stringResource(Res.string.w_engines),
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
            WEnginesListFilterCard(
                modifier = Modifier.weight(1f),
                uiState = uiState,
                invisibleFilter = adaptiveLayoutType == AdaptiveLayoutType.Compact,
                onWEngineClick = onWEngineClick,
                onRarityChipSelectionChanged = onRarityChipSelectionChanged,
                onSpecialtyChipSelectionChanged = onSpecialtyChipSelectionChanged
            )
        }
        if (showBottomSheet) {
            WEngineFilterBottomSheet(sheetState = sheetState,
                uiState = uiState,
                onRarityChipSelectionChanged = onRarityChipSelectionChanged,
                onSpecialtyChipSelectionChanged = onSpecialtyChipSelectionChanged,
                onDismiss = { showBottomSheet = false })
        }
    }
}