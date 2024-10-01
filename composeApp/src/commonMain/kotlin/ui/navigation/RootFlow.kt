/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation

sealed interface RootFlow {
    data object Splash : Screen(route = "splash")
    data object ZzzArchive : Screen(route = "zzzArchive")
}