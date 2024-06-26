/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: Apache-2.0
 */

package home

import androidx.compose.runtime.Composable
import ui.utils.ZzzArchiveContentType

@Composable
fun HomeScreen(
    contentType: ZzzArchiveContentType,
    onCharacterOverviewClick: () -> Unit,
    onWeaponOverviewClick: () -> Unit,
    onEchoesOverviewClick: () -> Unit,
    onCharacterDetailClick: (Long) -> Unit = {},
    onWeaponDetailClick: (Long) -> Unit = {},
    onEchoDetailClick: (Long) -> Unit = {},
) {
    if (contentType == ZzzArchiveContentType.SINGLE) {
        HomeScreenSingle(
            onCharacterOverviewClick = onCharacterOverviewClick,
            onWeaponOverviewClick = onWeaponOverviewClick,
            onEchoesOverviewClick = onEchoesOverviewClick,
            onCharacterDetailClick = onCharacterDetailClick,
            onWeaponDetailClick = onWeaponDetailClick,
            onEchoDetailClick = onEchoDetailClick,
        )
    } else {
        HomeScreenDual(
            onCharacterOverviewClick = onCharacterOverviewClick,
            onWeaponOverviewClick = onWeaponOverviewClick,
            onEchoesOverviewClick = onEchoesOverviewClick,
            onCharacterDetailClick = onCharacterDetailClick,
            onWeaponDetailClick = onWeaponDetailClick,
            onEchoDetailClick = onEchoDetailClick,
        )
    }
}