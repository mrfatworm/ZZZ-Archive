/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.component.ContentCard
import ui.component.SimpleItem
import ui.component.ViewAllCardHeader
import ui.data.SimpleListItemState
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.w_engines

@Composable
fun WEnginesListCard(
    wEnginesList: List<SimpleListItemState>,
    onWEnginesOverviewClick: () -> Unit,
    onWEngineDetailClick: (Int) -> Unit
) {
    ContentCard(modifier = Modifier.fillMaxWidth()) {
        ViewAllCardHeader(modifier = Modifier.fillMaxWidth(),
            titleRes = Res.string.w_engines,
            onActionClick = {
                onWEnginesOverviewClick()
            })
        LazyRow(
            contentPadding = PaddingValues(
                top = AppTheme.dimens.paddingUnderCardHeader,
                start = AppTheme.dimens.paddingCard,
                end = AppTheme.dimens.paddingCard,
                bottom = AppTheme.dimens.paddingCard
            )
        ) {
            items(items = wEnginesList) { item ->
                SimpleItem(
                    modifier = Modifier.clickable { onWEngineDetailClick(item.id) },
                    rarity = item.rarity,
                    name = item.name,
                    imgUrl = ""
                )
                Spacer(modifier = Modifier.size(AppTheme.dimens.gapImageProfileList))
            }
        }
    }
}