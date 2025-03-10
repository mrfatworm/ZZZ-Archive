/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.hoyolab.components.my_agent_detail.MyAgentImageCard
import feature.hoyolab.components.my_agent_detail.MyAgentSkillColumnCard
import feature.hoyolab.components.my_agent_detail.MyAgentWeaponScoreCard
import feature.hoyolab.model.my_agent_detail.MyAgentDetailState
import ui.components.TopBarRound
import ui.theme.AppTheme
import ui.utils.contentGap
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding

@Composable
fun MyAgentDetailScreenDual(
    uiState: MyAgentDetailState,
    onAction: (MyAgentDetailAction) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxSize().background(AppTheme.colors.surface)
            .padding(horizontalSafePadding()),
        horizontalArrangement = Arrangement.spacedBy(contentGap())
    ) {
        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .padding(verticalSafePadding()),
            verticalArrangement = Arrangement.spacedBy(contentGap())
        ) {
            TopBarRound(title = uiState.agentDetail.name, onBackClick = {
                onAction(MyAgentDetailAction.ClickBack)
            })
            Row(
                modifier = Modifier.height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(contentGap())
            ) {
                MyAgentImageCard(
                    modifier = Modifier.weight(1f),
                    imageUrl = uiState.agentDetail.imageUrl,
                    name = uiState.agentDetail.name,
                    level = uiState.agentDetail.level,
                    rank = uiState.agentDetail.rank
                )
                MyAgentSkillColumnCard(skills = uiState.agentDetail.skills)
            }
            MyAgentWeaponScoreCard(
                weapon = uiState.agentDetail.weapon,
                hit = uiState.agentDetail.equipPlanInfo?.validPropertyCnt
            )
        }

        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .padding(verticalSafePadding()),
            verticalArrangement = Arrangement.spacedBy(contentGap())
        ) {
        }
    }
}



