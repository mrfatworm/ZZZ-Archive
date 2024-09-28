/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.agent.model.AgentListItem
import kotlinx.coroutines.launch
import ui.component.ContentCard
import ui.component.RarityItem
import ui.component.ViewAllCardHeader
import ui.component.ZzzIconButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agents
import zzzarchive.composeapp.generated.resources.ic_arrow_back
import zzzarchive.composeapp.generated.resources.ic_arrow_next
import zzzarchive.composeapp.generated.resources.next
import zzzarchive.composeapp.generated.resources.previous

@Composable
fun AgentsListCard(
    agentsList: List<AgentListItem>,
    onAgentsOverviewClick: () -> Unit,
    onAgentDetailClick: (Int) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Box {
        ContentCard(modifier = Modifier.fillMaxWidth()) {
            ViewAllCardHeader(
                modifier = Modifier.fillMaxWidth(),
                titleRes = Res.string.agents,
                onActionClick = {
                    onAgentsOverviewClick()
                })
            LazyRow(
                modifier = Modifier.hoverable(interactionSource = interactionSource),
                state = lazyListState,
                contentPadding = PaddingValues(
                    top = AppTheme.dimens.paddingUnderCardHeader,
                    start = AppTheme.dimens.paddingCard,
                    end = AppTheme.dimens.paddingCard,
                    bottom = AppTheme.dimens.paddingCard
                )
            ) {
                items(items = agentsList, key = { it.id }) { item ->
                    RarityItem(
                        modifier = Modifier.width(100.dp).clickable { onAgentDetailClick(item.id) },
                        rarityLevel = item.rarity,
                        name = item.name,
                        attribute = item.attribute,
                        imgUrl = item.getProfileUrl()
                    )
                    Spacer(modifier = Modifier.size(AppTheme.dimens.gapImageProfileList))
                }
            }
        }
        if (isHovered.value) {
            ZzzIconButton(
                Modifier.align(Alignment.CenterStart).padding(8.dp),
                iconRes = Res.drawable.ic_arrow_back,
                textRes = Res.string.previous,
                interactionSource = interactionSource
            ) {
                val targetIndex = lazyListState.firstVisibleItemIndex - 3
                coroutineScope.launch {
                    if (targetIndex >= 0) {
                        lazyListState.animateScrollToItem(targetIndex)
                    } else {
                        lazyListState.animateScrollToItem(0)
                    }
                }
            }
            ZzzIconButton(
                Modifier.align(Alignment.CenterEnd).padding(8.dp),
                iconRes = Res.drawable.ic_arrow_next,
                textRes = Res.string.next,
                interactionSource = interactionSource
            ) {
                val targetIndex = lazyListState.firstVisibleItemIndex + 3
                coroutineScope.launch {
                    if (targetIndex < agentsList.size) {
                        lazyListState.animateScrollToItem(targetIndex)
                    } else {
                        lazyListState.animateScrollToItem(agentsList.size - 1)
                    }
                }
            }
        }
    }

}