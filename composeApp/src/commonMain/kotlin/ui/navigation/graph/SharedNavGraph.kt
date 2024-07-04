/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: Apache-2.0
 */

package ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import artifact.ArtifactDetailScreen
import artifact.ArtifactsListScreen
import character.CharacterDetailScreen
import character.CharactersListScreen
import ui.navigation.RootScreen
import ui.navigation.SubScreen
import ui.utils.ZzzArchiveContentType
import weapon.WeaponDetailScreen
import weapon.WeaponsListScreen

fun NavGraphBuilder.sharedScreenDestination(
    navController: NavHostController,
    contentType: ZzzArchiveContentType,
    navigateToTopLevelDestination: (RootScreen) -> Unit
) {
    composable(SubScreen.CharactersList.route) {
        CharactersListScreen(contentType, onCharacterClick = {
            navController.navigate(
                SubScreen.CharacterDetail.route
            )
        })
    }

    composable(SubScreen.CharacterDetail.route) {
        CharacterDetailScreen(onWeaponClick = { navController.navigate(SubScreen.WeaponDetail.route) })
    }

    composable(SubScreen.WeaponsList.route) {
        WeaponsListScreen(onWeaponClick = {
            navController.navigate(
                SubScreen.WeaponDetail.route
            )
        })
    }

    composable(SubScreen.WeaponDetail.route) {
        WeaponDetailScreen(onCharacterClick = { navController.navigate(SubScreen.CharacterDetail.route) })
    }

    composable(SubScreen.ArtifactsList.route) {
        ArtifactsListScreen(onEchoClick = {
            navController.navigate(
                SubScreen.EchoDetail.route
            )
        })
    }

    composable(SubScreen.EchoDetail.route) {
        ArtifactDetailScreen(onCharacterClick = { navController.navigate(SubScreen.CharacterDetail.route) })
    }

}