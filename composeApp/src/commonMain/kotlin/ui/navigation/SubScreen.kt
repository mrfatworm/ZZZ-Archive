/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: Apache-2.0
 */

package ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class SubScreen(
    val route: String,
    val text: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Home : SubScreen(
        route = "homeOverview", text = "Home Overview"
    )

    data object CharactersList : SubScreen(
        route = "charactersList", text = "Characters List"
    )

    data object CharacterDetail : SubScreen(
        route = "characterDetail/{characterId}", text = "Character Detail", navArguments = listOf(navArgument("characterId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(characterId: String) = "characterDetail/${characterId}"
    }

    data object WeaponsList : SubScreen(
        route = "weaponsList", text = "Weapons List"
    )

    data object WeaponDetail : SubScreen(
        route = "weaponDetail/{weaponId}", text = "Weapon Detail", navArguments = listOf(navArgument("weaponId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(weaponId: String) = "weaponDetail/${weaponId}"
    }

    data object ArtifactsList : SubScreen(
        route = "artifactsList", text = "Artifacts List"
    )

    data object EchoDetail : SubScreen(
        route = "echoDetail/{echoId}", text = "Artifact Detail", navArguments = listOf(navArgument("echoId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(echoId: String) = "echoDetail/${echoId}"
    }

    data object Setting : SubScreen(
        route = "settingOverview", text = "Setting Overview"
    )

    data object Feedback : SubScreen(
        route = "feedbackOverview", text = "Feedback Overview"
    )

}