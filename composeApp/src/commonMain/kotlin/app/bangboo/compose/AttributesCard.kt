/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.bangboo.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.agent.compose.AttributeItem
import app.bangboo.model.BangbooDetailResponse
import org.jetbrains.compose.resources.stringResource
import ui.component.CardHeader
import ui.component.ContentCard
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.atk
import zzzarchive.composeapp.generated.resources.attributes
import zzzarchive.composeapp.generated.resources.def
import zzzarchive.composeapp.generated.resources.hp

@Composable
fun AttributesCard(agentDetail: BangbooDetailResponse) {
    ContentCard(hasDefaultPadding = false) {
        CardHeader(
            title = stringResource(Res.string.attributes).uppercase() + " Lv.Max"
        )
        AttributeItem(
            title = stringResource(Res.string.hp),
            content = agentDetail.basicData.hp.toString()
        )
        AttributeItem(
            title = stringResource(Res.string.atk),
            content = agentDetail.basicData.atk.toString()
        )
        AttributeItem(
            title = stringResource(Res.string.def),
            content = agentDetail.basicData.def.toString()
        )
        Spacer(Modifier.size(4.dp))
    }
}

