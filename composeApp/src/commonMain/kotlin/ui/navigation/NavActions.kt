/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

class NavActions(private val navController: NavHostController) {

    fun navigationTo(destination: Screen) {
        navController.navigate(destination.route)
    }

    fun navigationToTopAndSave(destination: Screen) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().route ?: "splash") {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigationToTop(destination: Screen) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().route ?: "splash") {
                inclusive = true
            }
        }
    }
}


