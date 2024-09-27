/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.component.RarityItem

@Composable
fun AgentsListScreenSingle(
    state: AgentsListState, onAgentClick: (Int) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(120.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(state.agentsList.size) { index ->
            val agent = state.agentsList[index]
            RarityItem(modifier = Modifier.size(100.dp).clickable { onAgentClick(agent.id) }.padding(top = 8.dp),
                name = agent.name,
                rarityLevel = agent.rarity,
                imgUrl = agent.imgUrl)
        }
    }
}