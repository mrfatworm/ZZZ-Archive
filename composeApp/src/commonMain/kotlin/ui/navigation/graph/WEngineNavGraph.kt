/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ui.navigation.Screen
import ui.utils.ContentType

@Composable
fun WEngineNavGraph(
    contentType: ContentType, navigateToTopLevelDestination: (Screen) -> Unit
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController, startDestination = Screen.WEnginesList.route
    ) {
        sharedScreenDestination(navController, contentType, navigateToTopLevelDestination)
    }
}