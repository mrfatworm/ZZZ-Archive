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
import ui.navigation.NavActions
import ui.navigation.graph.RootNavGraph
import ui.theme.ZzzArchiveTheme
import ui.utils.ContentType
import ui.utils.AdaptiveLayoutType
import utils.imageLoaderDiskCache


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ZzzArchiveApp() {
    // Initialize the Coil3 image loader
    setSingletonImageLoaderFactory { context ->
        imageLoaderDiskCache(context, false)
    }
    ZzzArchiveTheme {
        val adaptiveInfo = currentWindowAdaptiveInfo()
        val windowSizeClass = adaptiveInfo.windowSizeClass.windowWidthSizeClass
        val adaptiveLayoutType: AdaptiveLayoutType
        val contentType: ContentType
        when (windowSizeClass) {
            WindowWidthSizeClass.COMPACT -> {
                adaptiveLayoutType = AdaptiveLayoutType.Compact
                contentType = ContentType.Single
            }

            WindowWidthSizeClass.MEDIUM -> {
                adaptiveLayoutType = AdaptiveLayoutType.Medium
                contentType = ContentType.Single
            }

            WindowWidthSizeClass.EXPANDED -> {
                adaptiveLayoutType = AdaptiveLayoutType.Expanded
                contentType = ContentType.Dual
            }

            else -> {
                adaptiveLayoutType = AdaptiveLayoutType.Compact
                contentType = ContentType.Single
            }
        }
        val rootNavController = rememberNavController()
        val rootNavActions = remember(rootNavController) {
            NavActions(rootNavController)
        }

        RootNavGraph(
            rootNavController = rootNavController,
            rootNavActions = rootNavActions,
            adaptiveLayoutType = adaptiveLayoutType,
            contentType = contentType
        )
    }
}