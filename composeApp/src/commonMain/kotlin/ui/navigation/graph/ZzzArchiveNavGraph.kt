/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: Apache-2.0
 */

package ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import feedback.FeedbackScreen
import ui.navigation.RootScreen
import ui.navigation.ZzzArchiveNavigationActions
import ui.utils.ZzzArchiveContentType

@Composable
fun ZzzArchiveNavGraph(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController,
    contentType: ZzzArchiveContentType,
    navigationActions: ZzzArchiveNavigationActions,
) {
    NavHost(
        modifier = modifier,
        navController = rootNavController,
        startDestination = RootScreen.Home.route,
    ) {
        composable(RootScreen.Home.route) {
            HomeNavHost(contentType = contentType,
                navigateToTopLevelDestination = { navigationActions.navigationToTopAndSave(it) })
        }
        composable(RootScreen.Agents.route) {
            CharacterNavGraph(contentType, navigateToTopLevelDestination = {
                navigationActions.navigationToTopAndSave(it)
            })
        }
        composable(RootScreen.Weapons.route) {
            WeaponNavGraph(contentType, navigateToTopLevelDestination = {
                navigationActions.navigationToTopAndSave(it)
            })
        }
        composable(RootScreen.Drivers.route) {
            ArtifactNavGraph(contentType, navigateToTopLevelDestination = {
                navigationActions.navigationToTopAndSave(it)
            })
        }

        composable(RootScreen.Setting.route) {
            SettingNavGraph(contentType = contentType)
        }
        composable(RootScreen.Feedback.route) {
            FeedbackScreen()
        }
    }
}
