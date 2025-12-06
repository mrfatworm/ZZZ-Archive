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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.hoyolab.components.agent.MyAgentDrivesCard
import feature.hoyolab.components.agent.MyAgentFooterCard
import feature.hoyolab.components.agent.MyAgentImageCard
import feature.hoyolab.components.agent.MyAgentPropertiesCard
import feature.hoyolab.components.agent.MyAgentSkillCard
import feature.hoyolab.components.agent.MyAgentWeaponScoreCard
import feature.hoyolab.model.agent.MyAgentDetailState
import ui.theme.AppTheme
import ui.utils.contentGap
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding

@Composable
fun MyAgentDetailScreenSingle(
    uiState: MyAgentDetailState,
    onAction: (MyAgentDetailAction) -> Unit
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(AppTheme.colors.surface)
                .padding(horizontalSafePadding())
                .padding(verticalSafePadding()),
        verticalArrangement = Arrangement.spacedBy(contentGap())
    ) {
        MyAgentImageCard(
            modifier =
                Modifier.heightIn(
                    max = AppTheme.size.s240
                ),
            uiState = uiState,
            onAction = onAction
        )
        MyAgentSkillCard(skills = uiState.agentDetail.skills)

        MyAgentWeaponScoreCard(
            weapon = uiState.agentDetail.weapon,
            equipPlan = uiState.agentDetail.equipPlanInfo
        )
        MyAgentPropertiesCard(
            properties = uiState.agentDetail.properties,
            planProperties = uiState.planProperties
        )
        MyAgentDrivesCard(drives = uiState.agentDetail.equip)
        MyAgentFooterCard(
            modifier = Modifier.fillMaxWidth().height(AppTheme.size.s100)
        )
    }
}
