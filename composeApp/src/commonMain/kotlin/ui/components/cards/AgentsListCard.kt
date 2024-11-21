/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import feature.agent.model.AgentListItem
import org.jetbrains.compose.resources.stringResource
import ui.components.items.RarityItem
import ui.components.items.RowListEndItem
import ui.theme.AppTheme
import ui.utils.drawRowListMask
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agents
import zzzarchive.composeapp.generated.resources.all_agents

@Composable
fun AgentsListCard(
    agentsList: List<AgentListItem>,
    showViewAll: Boolean = false,
    onAgentsOverviewClick: () -> Unit,
    onAgentDetailClick: (Int) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()
    val lazyListState = rememberLazyListState()
    ContentCard(
        modifier = Modifier.fillMaxWidth().hoverable(interactionSource = interactionSource),
        hasDefaultPadding = false
    ) {
        HoveredIndicatorHeader(
            title = stringResource(Res.string.agents),
            isHovered = isHovered.value,
            lazyListState = lazyListState,
        ) {
            if (showViewAll) {
                Text(
                    modifier = Modifier.clip(RoundedCornerShape(8.dp))
                        .clickable { onAgentsOverviewClick() }.pointerHoverIcon(PointerIcon.Hand)
                        .background(AppTheme.colors.surface)
                        .border(1.dp, AppTheme.colors.border, RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    text = stringResource(Res.string.all_agents),
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colors.onSurface
                )
            }
        }
        LazyRow(
            modifier = Modifier.drawRowListMask(
                colorScheme = AppTheme.colors,
                startEnable = lazyListState.canScrollBackward,
                endEnable = lazyListState.canScrollForward
            ), state = lazyListState, contentPadding = PaddingValues(
                top = AppTheme.dimens.paddingUnderCardHeader,
                start = AppTheme.dimens.paddingCard,
                end = AppTheme.dimens.paddingCard,
                bottom = AppTheme.dimens.paddingCard
            )
        ) {
            items(items = agentsList, key = { it.id }) { agent ->
                RarityItem(
                    modifier = Modifier.animateItem(),
                    rarityLevel = agent.rarity,
                    name = agent.name,
                    attribute = agent.getAttributeEnum(),
                    imgUrl = agent.getProfileUrl(),
                    onClick = {
                        onAgentDetailClick(agent.id)
                    })
                Spacer(modifier = Modifier.size(AppTheme.dimens.gapImageProfileList))
            }
            item {
                RowListEndItem(text = stringResource(Res.string.all_agents)) {
                    onAgentsOverviewClick()
                }
            }
        }
    }
}