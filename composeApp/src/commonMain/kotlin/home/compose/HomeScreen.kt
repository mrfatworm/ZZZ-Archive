/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package home.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
    val uiState = viewModel.uiState.collectAsState()
    LaunchedEffect(true) {
        viewModel.getActivityTitle()
    }
    if (contentType == ZzzArchiveContentType.SINGLE) {
        HomeScreenSingle(
            uiState = uiState.value,
            onCharacterOverviewClick = onCharacterOverviewClick,
            onWeaponOverviewClick = onWeaponOverviewClick,
            onEchoesOverviewClick = onEchoesOverviewClick,
            onCharacterDetailClick = onCharacterDetailClick,
            onWeaponDetailClick = onWeaponDetailClick,
            onEchoDetailClick = onEchoDetailClick,
        )
    } else {
        HomeScreenDual(
            uiState = uiState.value,
            onCharacterOverviewClick = onCharacterOverviewClick,
            onWeaponOverviewClick = onWeaponOverviewClick,
            onEchoesOverviewClick = onEchoesOverviewClick,
            onCharacterDetailClick = onCharacterDetailClick,
            onWeaponDetailClick = onWeaponDetailClick,
            onEchoDetailClick = onEchoDetailClick,
        )
    }
}