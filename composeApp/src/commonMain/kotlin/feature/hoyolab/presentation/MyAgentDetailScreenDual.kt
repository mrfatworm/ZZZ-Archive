/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.hoyolab.components.my_agent_detail.MyAgentDetailTopBar
import feature.hoyolab.components.my_agent_detail.MyAgentDrivesCard
import feature.hoyolab.components.my_agent_detail.MyAgentFooterCard
import feature.hoyolab.components.my_agent_detail.MyAgentImageCard
import feature.hoyolab.components.my_agent_detail.MyAgentPropertiesCard
import feature.hoyolab.components.my_agent_detail.MyAgentSkillColumnCard
import feature.hoyolab.components.my_agent_detail.MyAgentWeaponScoreCard
import feature.hoyolab.model.my_agent_detail.MyAgentDetailState
import ui.theme.AppTheme
import ui.utils.contentGap
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding

@Composable
fun MyAgentDetailScreenDual(
    uiState: MyAgentDetailState,
    onAction: (MyAgentDetailAction) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().background(AppTheme.colors.surface)
            .padding(horizontalSafePadding()),
        horizontalArrangement = Arrangement.spacedBy(contentGap())
    ) {
        Column(
            modifier = Modifier.weight(1f).padding(verticalSafePadding()),
            verticalArrangement = Arrangement.spacedBy(contentGap())
        ) {

            MyAgentDetailTopBar(uiState, onAction)
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(contentGap())
            ) {
                MyAgentImageCard(
                    modifier = Modifier.weight(1f),
                    uiState = uiState,
                    onApply = { onAction(MyAgentDetailAction.AdjustImageDone) }
                )
                MyAgentSkillColumnCard(
                    modifier = Modifier.fillMaxHeight(), skills = uiState.agentDetail.skills
                )
            }
            MyAgentWeaponScoreCard(
                weapon = uiState.agentDetail.weapon,
                hit = uiState.agentDetail.equipPlanInfo?.validPropertyCnt
            )
        }

        Column(
            modifier = Modifier.weight(1f).padding(verticalSafePadding()),
            verticalArrangement = Arrangement.spacedBy(contentGap())
        ) {
            MyAgentPropertiesCard(
                properties = uiState.agentDetail.properties,
                planProperties = uiState.planProperties
            )
            MyAgentDrivesCard(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                drives = uiState.agentDetail.equip
            )
            MyAgentFooterCard(modifier = Modifier.weight(1f).fillMaxWidth())
        }
    }
}



