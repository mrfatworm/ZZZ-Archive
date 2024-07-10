/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: Apache-2.0
 */

package home.compose

import androidx.compose.runtime.Composable
import home.domain.ZzzViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import ui.utils.ZzzArchiveContentType

@OptIn(KoinExperimentalAPI::class)
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
    val viewModel = koinViewModel<ZzzViewModel>()
    if (contentType == ZzzArchiveContentType.SINGLE) {
        HomeScreenSingle(
            viewModelText = viewModel.getImageUrl(),
            onCharacterOverviewClick = onCharacterOverviewClick,
            onWeaponOverviewClick = onWeaponOverviewClick,
            onEchoesOverviewClick = onEchoesOverviewClick,
            onCharacterDetailClick = onCharacterDetailClick,
            onWeaponDetailClick = onWeaponDetailClick,
            onEchoDetailClick = onEchoDetailClick,
        )
    } else {
        HomeScreenDual(
            viewModelText = viewModel.getImageUrl(),
            onCharacterOverviewClick = onCharacterOverviewClick,
            onWeaponOverviewClick = onWeaponOverviewClick,
            onEchoesOverviewClick = onEchoesOverviewClick,
            onCharacterDetailClick = onCharacterDetailClick,
            onWeaponDetailClick = onWeaponDetailClick,
            onEchoDetailClick = onEchoDetailClick,
        )
    }
}