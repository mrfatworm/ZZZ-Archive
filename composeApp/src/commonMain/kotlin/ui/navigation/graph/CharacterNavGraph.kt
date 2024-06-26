/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: Apache-2.0
 */

package ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ui.navigation.RootScreen
import ui.navigation.SubScreen
import ui.utils.ZzzArchiveContentType

@Composable
fun CharacterNavGraph(
    contentType: ZzzArchiveContentType, navigateToTopLevelDestination: (RootScreen) -> Unit) {
    val characterNavController = rememberNavController()
    NavHost(
        navController = characterNavController, startDestination = SubScreen.CharactersList.route
    ) {
        sharedScreenDestination(characterNavController, contentType,navigateToTopLevelDestination)
    }
}