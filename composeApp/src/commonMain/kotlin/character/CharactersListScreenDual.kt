/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: Apache-2.0
 */

package character

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
fun CharactersListScreenDual(
    state: CharactersListState, onCharacterClick: (Long) -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(state.charactersList.size) { index ->
                val character = state.charactersList[index]
                SimpleItem(modifier = Modifier.padding(top = 8.dp),
                    isLargeSize = true,
                    name = character.name,
                    rarity = character.rarity,
                    imgRes = character.imageRes,
                    onClick = { onCharacterClick(character.id) })
            }
        }
    }
}