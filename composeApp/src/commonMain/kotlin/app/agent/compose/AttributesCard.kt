/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.agent.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.agent.model.AgentDetailResponse
import org.jetbrains.compose.resources.stringResource
import ui.component.CardHeader
import ui.component.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.attributes
import zzzarchive.composeapp.generated.resources.hp_atk_def

@Composable
fun AttributesCard(agentDetail: AgentDetailResponse) {
    ContentCard(hasDefaultPadding = false) {
        CardHeader(
            title = stringResource(Res.string.attributes).uppercase() + " Lv.Max"
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

@Composable
fun AttributeItem(title: String, content: String) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.labelLarge
        )
        Text(
            modifier = Modifier.weight(1f),
            text = content,
            textAlign = TextAlign.End,
            color = AppTheme.colors.onSurface,
            style = AppTheme.typography.titleMedium
        )
    }
}
