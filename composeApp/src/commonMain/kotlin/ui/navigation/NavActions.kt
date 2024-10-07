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

    fun back() {
        navController.popBackStack()
    }

    fun navigationToMainScreen(destination: MainFlow) {
        navController.navigate(destination.route) {
            popUpTo(MainFlow.Home.startScreen.route) {
                inclusive = MainFlow.Home.route == destination.route
            }
        }
    }

    fun popAndNavigation(destination: Screen) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().route ?: "home_flow") {
                this.inclusive = true
            }
        }
    }

    @Deprecated("Only work on Android - last test: 2.8.0-alpha10")
    fun navigationToTopAndSave(destination: Screen) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().route ?: "splash") {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}


