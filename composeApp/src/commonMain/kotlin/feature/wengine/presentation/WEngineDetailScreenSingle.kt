/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.wengine.components.WEngineAttributesCard
import feature.wengine.components.WEngineImageCard
import feature.wengine.model.WEngineDetailState
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.HighLightTextCard
import ui.components.cards.MaterialsListCard
import ui.components.cards.TextCard
import ui.utils.contentGap
import ui.utils.contentPadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.additional_info
import zzzarchive.composeapp.generated.resources.w_engine_effect

@Composable
fun WEngineDetailScreenSingle(
    uiState: WEngineDetailState,
    onAction: (WEngineDetailAction) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .contentPadding(),
        verticalArrangement = Arrangement.spacedBy(contentGap())
    ) {
        WEngineImageCard(uiState.wEngineDetail) {
            onAction(WEngineDetailAction.ClickBack)
        }
        WEngineAttributesCard(uiState.wEngineDetail)
        HighLightTextCard(stringResource(Res.string.w_engine_effect), uiState.wEngineDetail.skill)
        MaterialsListCard(uiState.wEngineDetail.levelMaterials)
        TextCard(stringResource(Res.string.additional_info), uiState.wEngineDetail.background)
    }
}