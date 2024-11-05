/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.wengine.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.agent.compose.TextCard
import app.wengine.model.WEngineDetailState
import org.jetbrains.compose.resources.stringResource
import ui.component.MaterialsCard
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.additional_info
import zzzarchive.composeapp.generated.resources.w_engine_effect

@Composable
fun WEngineDetailScreenSingle(
    uiState: WEngineDetailState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .contentPadding(adaptiveLayoutType, AppTheme.dimens),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        WEngineImageCard(uiState.wEngineDetail, onBackClick)
        AttributesCard(uiState.wEngineDetail)
        TextCard(stringResource(Res.string.w_engine_effect), uiState.wEngineDetail.skill)
        MaterialsCard(uiState.wEngineDetail.levelMaterials)
        TextCard(stringResource(Res.string.additional_info), uiState.wEngineDetail.background)
    }
}