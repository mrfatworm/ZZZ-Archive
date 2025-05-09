/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.agent.model.AgentDetail
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.CardHeader
import ui.components.cards.ContentCard
import ui.components.items.AttributeItem
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.attributes
import zzzarchive.composeapp.generated.resources.hp_atk_def

@Composable
fun AgentAttributesCard(agentDetail: AgentDetail) {
    ContentCard(hasDefaultPadding = false) {
        CardHeader(
            title = stringResource(Res.string.attributes) + " Lv.Max"
        )
        AttributeItem(
            title = stringResource(Res.string.hp_atk_def),
            content = agentDetail.getHpAtkDef()
        )
        agentDetail.basicData.nameAndValues.forEach {
            AttributeItem(title = it.name, content = it.value)
        }
        Spacer(Modifier.size(AppTheme.spacing.s200))
    }
}

