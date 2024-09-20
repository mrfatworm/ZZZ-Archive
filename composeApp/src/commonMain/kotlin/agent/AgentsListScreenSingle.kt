/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package agent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.component.SimpleItem

@Composable
fun AgentsListScreenSingle(
    state: AgentsListState, onAgentClick: (Long) -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(state.agentsList.size) { index ->
                val agent = state.agentsList[index]
                SimpleItem(modifier = Modifier
                    .clickable { onAgentClick(agent.id) }
                    .padding(top = 8.dp),
                    isLargeSize = true,
                    name = agent.name,
                    rarity = agent.rarity,
                    imgRes = agent.imageRes)
            }
        }
    }
}