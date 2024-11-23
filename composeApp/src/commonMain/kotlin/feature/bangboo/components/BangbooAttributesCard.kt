/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import feature.bangboo.model.BangbooDetailResponse
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.CardHeader
import ui.components.cards.ContentCard
import ui.components.items.AttributeItem
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.atk
import zzzarchive.composeapp.generated.resources.attributes
import zzzarchive.composeapp.generated.resources.def
import zzzarchive.composeapp.generated.resources.hp

@Composable
fun BangbooAttributesCard(bangbooDetail: BangbooDetailResponse) {
    ContentCard(hasDefaultPadding = false) {
        CardHeader(
            title = stringResource(Res.string.attributes) + " Lv.Max"
        )
        AttributeItem(
            title = stringResource(Res.string.hp),
            content = bangbooDetail.basicData.hp.toString()
        )
        AttributeItem(
            title = stringResource(Res.string.atk),
            content = bangbooDetail.basicData.atk.toString()
        )
        AttributeItem(
            title = stringResource(Res.string.def),
            content = bangbooDetail.basicData.def.toString()
        )
        Spacer(Modifier.size(4.dp))
    }
}

