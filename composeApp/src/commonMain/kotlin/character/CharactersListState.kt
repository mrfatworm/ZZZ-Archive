/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package character

import ui.data.SimpleListItemState
import ui.data.stubCharactersList


data class CharactersListState(
    val charactersList: List<SimpleListItemState> = emptyList()
)

val stubCharactersListState = CharactersListState(
    charactersList = stubCharactersList + stubCharactersList + stubCharactersList + stubCharactersList
)
