/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.hoyolab.components.my_agent_detail.MyAgentDrivesCard
import feature.hoyolab.components.my_agent_detail.MyAgentFooterCard
import feature.hoyolab.components.my_agent_detail.MyAgentImageCard
import feature.hoyolab.components.my_agent_detail.MyAgentPropertiesCard
import feature.hoyolab.components.my_agent_detail.MyAgentSkillRowCard
import feature.hoyolab.components.my_agent_detail.MyAgentWeaponScoreCard
import feature.hoyolab.model.my_agent_detail.MyAgentDetailState
import ui.components.TopBarRound
import ui.theme.AppTheme
import ui.utils.contentGap
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding

@Composable
fun MyAgentDetailScreenSingle(
    uiState: MyAgentDetailState, onAction: (MyAgentDetailAction) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .background(AppTheme.colors.surface).padding(horizontalSafePadding())
            .padding(verticalSafePadding()),
        verticalArrangement = Arrangement.spacedBy(contentGap())
    ) {
        TopBarRound(uiState.agentDetail.name, onBackClick = {
            onAction(MyAgentDetailAction.ClickBack)
        })
        MyAgentImageCard(
            modifier = Modifier,
            imageUrl = uiState.agentDetail.imageUrl,
            name = uiState.agentDetail.name,
            level = uiState.agentDetail.level,
            rank = uiState.agentDetail.rank
        )
        MyAgentSkillRowCard(skills = uiState.agentDetail.skills)

        MyAgentWeaponScoreCard(
            weapon = uiState.agentDetail.weapon,
            hit = uiState.agentDetail.equipPlanInfo?.validPropertyCnt
        )
        MyAgentPropertiesCard(
            properties = uiState.agentDetail.properties,
            planProperties = uiState.agentDetail.equipPlanInfo?.gameDefault?.propertyList
                ?: emptyList()
        )
        MyAgentDrivesCard(
            drives = uiState.agentDetail.equip,
            planProperties = uiState.agentDetail.equipPlanInfo?.gameDefault?.propertyList
                ?: emptyList()
        )
        MyAgentFooterCard(
            modifier = Modifier.fillMaxWidth().height(AppTheme.size.rarityItemMediumSize)
        )
    }
}