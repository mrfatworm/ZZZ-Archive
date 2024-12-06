/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.wengine.model.WEngineDetail
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.CardHeader
import ui.components.cards.ContentCard
import ui.components.items.AttributeItem
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.atk
import zzzarchive.composeapp.generated.resources.attributes

@Composable
fun WEngineAttributesCard(wEngineDetail: WEngineDetail) {
    ContentCard(hasDefaultPadding = false) {
        CardHeader(
            title = stringResource(Res.string.attributes) + " Lv.Max"
        )
        AttributeItem(
            title = stringResource(Res.string.atk),
            content = wEngineDetail.atk.toString()
        )
        AttributeItem(
            title = wEngineDetail.stat.name,
            content = wEngineDetail.stat.value
        )
        Spacer(Modifier.size(AppTheme.spacing.s200))
    }
}

