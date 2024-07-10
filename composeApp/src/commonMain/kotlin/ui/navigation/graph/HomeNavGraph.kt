/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: Apache-2.0
 */

package ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import home.compose.HomeScreen
import ui.navigation.RootScreen
import ui.navigation.SubScreen
import ui.utils.ZzzArchiveContentType

@Composable
fun HomeNavHost(
    contentType: ZzzArchiveContentType, navigateToTopLevelDestination: (RootScreen) -> Unit
) {
    val homeNavController = rememberNavController()
    NavHost(navController = homeNavController, startDestination = SubScreen.Home.route) {
        if (contentType == ZzzArchiveContentType.DUAL) {
            composable(SubScreen.Home.route) {
                HomeScreen(contentType = contentType,
                    onCharacterOverviewClick = { navigateToTopLevelDestination(RootScreen.Characters) },
                    onWeaponOverviewClick = { navigateToTopLevelDestination(RootScreen.Weapons) },
                    onEchoesOverviewClick = { navigateToTopLevelDestination(RootScreen.Artifacts) },
                    onCharacterDetailClick = { homeNavController.navigate(SubScreen.CharacterDetail.route) },
                    onWeaponDetailClick = { homeNavController.navigate(SubScreen.WeaponDetail.route) },
                    onEchoDetailClick = { homeNavController.navigate(SubScreen.EchoDetail.route) })
            }
        } else {
            composable(SubScreen.Home.route) {
                HomeScreen(contentType = contentType,
                    onCharacterOverviewClick = { homeNavController.navigate(SubScreen.CharactersList.route) },
                    onWeaponOverviewClick = { homeNavController.navigate(SubScreen.WeaponsList.route) },
                    onEchoesOverviewClick = { homeNavController.navigate(SubScreen.ArtifactsList.route) },
                    onCharacterDetailClick = { homeNavController.navigate(SubScreen.CharacterDetail.route) },
                    onWeaponDetailClick = { homeNavController.navigate(SubScreen.WeaponDetail.route) },
                    onEchoDetailClick = { homeNavController.navigate(SubScreen.EchoDetail.route) })
            }
        }
        sharedScreenDestination(homeNavController, contentType, navigateToTopLevelDestination)
    }
}