/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.component.RarityItem
import ui.data.SimpleListItemState
import ui.data.sampleAgentsList
import ui.theme.AppTheme
import ui.theme.ZzzArchiveTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_arrow_next
import zzzarchive.composeapp.generated.resources.view_all

@Composable
fun HomeListSection(
    titleText: String,
    items: List<SimpleListItemState>,
    onSeeAllClick: () -> Unit,
    onItemClick: (Int) -> Unit,
) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = titleText,
                style = AppTheme.typography.titleMedium,
                color = AppTheme.colors.onSurface
            )
            Row(modifier = Modifier
                .clickable { onSeeAllClick() }
                .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(Res.string.view_all),
                    style = AppTheme.typography.labelLarge,
                    color = AppTheme.colors.onSurfaceVariant
                )
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_arrow_next),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurface
                )
            }
        }

        LazyRow(
            contentPadding = PaddingValues(4.dp)
        ) {
            item {
                Spacer(modifier = Modifier.padding(8.dp))
            }
            items(items = items) { item ->
                RarityItem(modifier = Modifier
                    .clickable { onItemClick(item.id) }
                    .padding(horizontal = 4.dp),
                    rarityLevel = item.rarity,
                    name = item.name,
                    imgUrl = "")
            }
        }
    }
}

@Preview()
@Composable
fun HomeListSectionPreview() {
    ZzzArchiveTheme {
        HomeListSection(titleText = "Title",
            items = sampleAgentsList,
            onSeeAllClick = { }) {

        }
    }
}
