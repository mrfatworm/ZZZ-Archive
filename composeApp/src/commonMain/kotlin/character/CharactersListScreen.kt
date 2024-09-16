/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package character

import androidx.compose.runtime.Composable
import ui.utils.ZzzArchiveContentType

@Composable
fun CharactersListScreen(
    contentType: ZzzArchiveContentType, onCharacterClick: (Long) -> Unit = {}
) {
    if (contentType == ZzzArchiveContentType.SINGLE) {
        CharactersListScreenSingle(
            state = stubCharactersListState, onCharacterClick = onCharacterClick
        )
    } else {
        CharactersListScreenDual(
            state = stubCharactersListState, onCharacterClick = onCharacterClick
        )
    }
}