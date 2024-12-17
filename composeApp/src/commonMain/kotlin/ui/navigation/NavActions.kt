/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

class NavActions(private val navController: NavHostController) {

    fun navigationTo(destination: Screen) {
        navController.navigate(destination.route)
    }

    fun navigationToRoute(route: String) {
        navController.navigate(route)
    }

    fun back() {
        navController.popBackStack()
    }

    fun navigationToMainScreen(destination: MainFlow) {
        val thirdRoute = navController.currentBackStack.value.getOrNull(3)?.destination?.route
            ?: if (destination == MainFlow.Home) return else MainFlow.Home.route
        val forthRoute = navController.currentBackStack.value.getOrNull(4)?.destination?.route
        val fifthRoute = navController.currentBackStack.value.getOrNull(5)?.destination?.route
        val currentMainFlow =
            ALL_MAIN_FLOW.find { it.route == thirdRoute }?.route ?: MainFlow.Home.route
        val isAlreadyInDestination =
            ALL_MAIN_FLOW.find { it.startScreen.route == forthRoute } != null && fifthRoute == null && currentMainFlow == destination.route

        if (isAlreadyInDestination) {
            return
        }

        if (currentMainFlow == destination.route) {
            backToTopOfCurrentMainFlow(destination)
            return
        }

        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().route ?: Screen.Home.route) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun popAndNavigation(destination: Screen) {
        navController.navigate(destination.route) {
            popUpTo(navController.graph.findStartDestination().route ?: MainFlow.Home.route) {
                this.inclusive = true
            }
        }
    }

    private fun backToTopOfCurrentMainFlow(destination: MainFlow) {
        navController.popBackStack(route = destination.startScreen.route, inclusive = false)
    }
}


