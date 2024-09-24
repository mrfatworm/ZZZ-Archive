/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */


import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import com.mrfatworm.android.zzzarchive.ui.theme.ZzzArchiveTheme
import ui.navigation.NavActions
import ui.navigation.graph.RootNavGraph
import ui.utils.ContentType
import ui.utils.NavigationType
import utils.coilImageLoader


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ZzzArchiveApp() {
    setSingletonImageLoaderFactory { context ->
        coilImageLoader(context, false)
    }
    ZzzArchiveTheme {
        val adaptiveInfo = currentWindowAdaptiveInfo()
        val windowSizeClass = adaptiveInfo.windowSizeClass.windowWidthSizeClass
        val navigationType: NavigationType
        val contentType: ContentType
        when (windowSizeClass) {
            WindowWidthSizeClass.COMPACT -> {
                navigationType = NavigationType.BOTTOM_NAVIGATION
                contentType = ContentType.SINGLE
            }

            WindowWidthSizeClass.MEDIUM -> {
                navigationType = NavigationType.NAVIGATION_RAIL
                contentType = ContentType.SINGLE
            }

            WindowWidthSizeClass.EXPANDED -> {
                navigationType = NavigationType.NAVIGATION_DRAWER
                contentType = ContentType.DUAL
            }

            else -> {
                navigationType = NavigationType.BOTTOM_NAVIGATION
                contentType = ContentType.SINGLE
            }
        }
        val rootNavController = rememberNavController()
        val rootNavActions = remember(rootNavController) {
            NavActions(rootNavController)
        }

        RootNavGraph(
            rootNavController = rootNavController,
            rootNavActions = rootNavActions,
            navigationType = navigationType,
            contentType = contentType
        )
    }
}