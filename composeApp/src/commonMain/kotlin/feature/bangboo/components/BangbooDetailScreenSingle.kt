/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.bangboo.model.BangbooDetailState
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.MaterialsListCard
import ui.components.cards.TextCard
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.active_skill
import zzzarchive.composeapp.generated.resources.additional_ability
import zzzarchive.composeapp.generated.resources.chain_attack

@Composable
fun BangbooDetailScreenSingle(
    uiState: BangbooDetailState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .contentPadding(adaptiveLayoutType, AppTheme.dimens),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        BangbooImageCard(uiState.bangbooDetail, onBackClick)
        BangbooAttributesCard(uiState.bangbooDetail)
        MaterialsListCard(uiState.bangbooDetail.levelMaterials)
        TextCard(
            stringResource(Res.string.active_skill),
            uiState.bangbooDetail.activeSkill.description,
            uiState.bangbooDetail.activeSkill.name
        )
        TextCard(
            stringResource(Res.string.additional_ability),
            uiState.bangbooDetail.additionalAbility.description,
            uiState.bangbooDetail.additionalAbility.name
        )
        uiState.bangbooDetail.chainAttack?.let {
            TextCard(
                stringResource(Res.string.chain_attack),
                uiState.bangbooDetail.chainAttack.description,
                uiState.bangbooDetail.chainAttack.name
            )
        }
    }
}