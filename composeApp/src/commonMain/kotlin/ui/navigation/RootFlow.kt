/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.navigation

sealed interface RootFlow {
    data object Splash : Screen(route = "splash")
    data object MainContainer : Screen(route = "mainContainer")
}