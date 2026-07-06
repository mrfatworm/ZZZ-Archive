/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.panpf.sketch.SingletonSketch
import org.koin.compose.viewmodel.koinViewModel
import root.InitViewModel
import ui.navigation.graph.RootNavGraph
import ui.theme.AppTheme
import ui.theme.ZzzArchiveTheme
import utils.newSketch

@Composable
fun ZzzArchiveApp() {
    // Initialize the Sketch image loader singleton
    SingletonSketch.setSafe { context ->
        newSketch(context)
    }
    ZzzArchiveTheme {
        val viewModel: InitViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val themeController = AppTheme.themeController

        LaunchedEffect(uiState.isDark) {
            themeController.setTheme(uiState.isDark)
        }

        LaunchedEffect(uiState.uiScale, uiState.fontScale) {
            themeController.setUiScale(uiState.uiScale)
            themeController.setFontScale(uiState.fontScale)
        }

        if (!uiState.isLoading) {
            RootNavGraph()
        }
    }
}
