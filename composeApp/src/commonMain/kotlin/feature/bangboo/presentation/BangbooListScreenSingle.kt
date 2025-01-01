/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation

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
import feature.bangboo.components.BangbooFilterBottomSheet
import feature.bangboo.components.BangbooListFilterCard
import feature.bangboo.model.BangbooListState
import org.jetbrains.compose.resources.stringResource
import ui.components.TopBarScaffold
import ui.components.buttons.ZzzIconButton
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPaddingInScaffold
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.bangboo
import zzzarchive.composeapp.generated.resources.filter
import zzzarchive.composeapp.generated.resources.ic_filter
import zzzarchive.composeapp.generated.resources.ic_filter_filled

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BangbooListScreenSingle(
    uiState: BangbooListState,
    onAction: (BangbooListAction) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val isFiltered =
        uiState.selectedRarity.isNotEmpty() || uiState.selectedAttributes.isNotEmpty()
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        AnimatedVisibility(AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            TopBarScaffold(
                title = stringResource(Res.string.bangboo),
                onBackClick = {
                    onAction(BangbooListAction.ClickBack)
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
    }) { scaffoldPadding ->
        Column(
            modifier = Modifier.contentPaddingInScaffold(scaffoldPadding)
        ) {
            BangbooListFilterCard(
                modifier = Modifier.weight(1f),
                uiState = uiState,
                invisibleFilter = AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact,
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
        if (showBottomSheet) {
            BangbooFilterBottomSheet(sheetState = sheetState,
                uiState = uiState,
                onRarityChipSelectionChanged = {
                    onAction(BangbooListAction.ChangeRarityFilter(it))
                },
                onAttributeChipSelectionChanged = {
                    onAction(BangbooListAction.ChangeAttributeFilter(it))
                },
                onDismiss = { showBottomSheet = false })
        }
    }
}