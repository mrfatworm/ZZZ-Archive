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
fun ArtifactNavGraph(
    contentType: ZzzArchiveContentType, navigateToTopLevelDestination: (RootScreen) -> Unit) {
    val echoNavController = rememberNavController()
    NavHost(
        navController = echoNavController, startDestination = SubScreen.DriversList.route
    ) {
        sharedScreenDestination(echoNavController,contentType, navigateToTopLevelDestination)
    }
}