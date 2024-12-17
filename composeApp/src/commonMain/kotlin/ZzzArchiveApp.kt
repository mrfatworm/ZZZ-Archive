/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import coil3.compose.setSingletonImageLoaderFactory
import ui.navigation.NavActions
import ui.navigation.graph.RootNavGraph
import ui.theme.ZzzArchiveTheme
import utils.imageLoaderDiskCache


@Composable
fun ZzzArchiveApp() {
    // Initialize the Coil3 image loader
    setSingletonImageLoaderFactory { context ->
        imageLoaderDiskCache(context)
    }
    ZzzArchiveTheme {
        val rootNavController = rememberNavController()
        val rootNavActions = remember(rootNavController) {
            NavActions(rootNavController)
        }

        RootNavGraph(
            rootNavController = rootNavController,
            rootNavActions = rootNavActions
        )
    }
}