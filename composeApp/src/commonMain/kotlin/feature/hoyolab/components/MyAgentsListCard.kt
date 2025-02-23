/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.hoyolab.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.hoyolab.model.MyAgentsListState
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import ui.utils.cardPadding
import ui.utils.gridListHorizontalGap
import ui.utils.gridListVerticalGap

@Composable
fun MyAgentsListCard(
    modifier: Modifier,
    uiState: MyAgentsListState,
    onAgentClick: (Int) -> Unit
) {
    ContentCard(
        modifier = modifier,
        hasDefaultPadding = false,
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(AppTheme.size.rarityItemMediumSize),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(cardPadding()),
            horizontalArrangement = gridListHorizontalGap(),
            verticalArrangement = gridListVerticalGap()
        ) {
            items(
                count = uiState.agentsList.size,
                key = { index -> uiState.agentsList[index].id }) { index ->
                val agent = uiState.agentsList[index]
                MyAgentItem(
                    modifier = Modifier.animateItem(),
                    rarity = agent.rarity,
                    name = agent.name,
                    level = agent.level,
                    rank = agent.rank,
                    imgUrl = agent.imageUrl,
                    onClick = {
                        onAgentClick(agent.id)
                    })
            }
        }
    }
}