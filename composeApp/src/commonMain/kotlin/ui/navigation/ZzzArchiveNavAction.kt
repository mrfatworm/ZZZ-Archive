/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: Apache-2.0
 */

package ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController


class ZzzArchiveNavigationActions(private val navController: NavHostController) {

    fun navigationToTopAndSave(destination: RootScreen) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().route ?: "Home") {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigationToTop(destination: RootScreen) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().route ?: "Home") {
                inclusive = true
            }
        }
    }
}

val TOP_LEVEL_DESTINATIONS_MEDIUM = listOf(
    RootScreen.Home,
    RootScreen.Agents,
    RootScreen.Weapons,
    RootScreen.Drivers,
    RootScreen.Setting,
    RootScreen.Feedback
)

val TOP_LEVEL_DESTINATIONS_COMPACT = listOf(
    RootScreen.Home,
    RootScreen.Setting,
)


