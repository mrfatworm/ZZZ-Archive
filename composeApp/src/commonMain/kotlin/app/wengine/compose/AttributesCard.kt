/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.agent.compose.AttributeItem
import app.wengine.model.WEngineDetailResponse
import org.jetbrains.compose.resources.stringResource
import ui.component.CardHeader
import ui.component.ContentCard
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.atk
import zzzarchive.composeapp.generated.resources.attributes

@Composable
fun AttributesCard(agentDetail: WEngineDetailResponse) {
    ContentCard(hasDefaultPadding = false) {
        CardHeader(
            title = stringResource(Res.string.attributes).uppercase() + " Lv.Max"
        )
        AttributeItem(
            title = stringResource(Res.string.atk),
            content = agentDetail.atk.toString()
        )
        AttributeItem(
            title = agentDetail.stat.name,
            content = agentDetail.stat.value
        )
        Spacer(Modifier.size(4.dp))
    }
}

