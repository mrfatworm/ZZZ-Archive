/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.wengine.model.WEngineDetailState
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.MaterialsListCard
import ui.components.cards.TextCard
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.additional_info
import zzzarchive.composeapp.generated.resources.w_engine_effect

@Composable
fun WEngineDetailScreenDual(
    uiState: WEngineDetailState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onBackClick: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)) {
        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .contentPadding(adaptiveLayoutType, AppTheme.dimens),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
        ) {
            WEngineImageCard(uiState.wEngineDetail, onBackClick)
            WEngineAttributesCard(uiState.wEngineDetail)
        }

        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .contentPadding(adaptiveLayoutType, AppTheme.dimens),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
        ) {
            TextCard(stringResource(Res.string.w_engine_effect), uiState.wEngineDetail.skill)
            MaterialsListCard(uiState.wEngineDetail.levelMaterials)
            TextCard(stringResource(Res.string.additional_info), uiState.wEngineDetail.background)
        }
    }
}



