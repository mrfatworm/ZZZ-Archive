/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.bangboo.components.BangbooAttributesCard
import feature.bangboo.components.BangbooImageCard
import feature.bangboo.model.BangbooDetailState
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.HighLightTextCard
import ui.components.cards.MaterialsListCard
import ui.utils.contentGap
import ui.utils.contentPadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.active_skill
import zzzarchive.composeapp.generated.resources.additional_ability
import zzzarchive.composeapp.generated.resources.chain_attack

@Composable
fun BangbooDetailScreenSingle(
    uiState: BangbooDetailState,
    onAction: (BangbooDetailAction) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).contentPadding(),
        verticalArrangement = Arrangement.spacedBy(contentGap())
    ) {
        BangbooImageCard(uiState.bangbooDetail) {
            onAction(BangbooDetailAction.ClickBack)
        }
        BangbooAttributesCard(uiState.bangbooDetail)
        MaterialsListCard(uiState.bangbooDetail.levelMaterials)
        HighLightTextCard(
            stringResource(Res.string.active_skill),
            uiState.bangbooDetail.activeSkill.description,
            uiState.bangbooDetail.activeSkill.name
        )
        HighLightTextCard(
            stringResource(Res.string.additional_ability),
            uiState.bangbooDetail.additionalAbility.description,
            uiState.bangbooDetail.additionalAbility.name
        )
        uiState.bangbooDetail.chainAttack?.let {
            HighLightTextCard(
                stringResource(Res.string.chain_attack),
                uiState.bangbooDetail.chainAttack.description,
                uiState.bangbooDetail.chainAttack.name
            )
        }
    }
}