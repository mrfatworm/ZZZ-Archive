/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.navigation.graph.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import ui.navigation.MainFlow
import ui.navigation.NavActions
import ui.navigation.graph.sharedNavGraph
import ui.utils.AdaptiveLayoutType
import ui.utils.ContentType

fun NavGraphBuilder.bangbooNavGraph(
    contentType: ContentType,
    adaptiveLayoutType: AdaptiveLayoutType,
    navActions: NavActions
) {
    navigation(
        route = MainFlow.Bangboo.route, startDestination = MainFlow.Bangboo.startScreen.route
    ) {
        sharedNavGraph(contentType, adaptiveLayoutType, navActions)
    }
}