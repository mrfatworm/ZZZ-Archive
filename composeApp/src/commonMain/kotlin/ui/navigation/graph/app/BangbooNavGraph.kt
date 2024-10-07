/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.graph.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import ui.navigation.MainFlow
import ui.navigation.NavActions
import ui.navigation.graph.sharedComposable
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
        sharedComposable(contentType, adaptiveLayoutType, navActions)
    }
}