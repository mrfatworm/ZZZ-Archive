/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.agent.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.agent.model.AgentDetailResponse
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.CardHeader
import ui.components.cards.ContentCard
import ui.components.items.AttributeItem
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.attributes
import zzzarchive.composeapp.generated.resources.hp_atk_def

@Composable
fun AgentAttributesCard(agentDetail: AgentDetailResponse) {
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
        Spacer(Modifier.size(4.dp))
    }
}

