/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.panpf.sketch.SingletonSketch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import root.InitViewModel
import ui.navigation.graph.RootNavGraph
import ui.theme.AppTheme
import ui.theme.LocalSnackbarController
import ui.theme.ZzzArchiveTheme
import utils.newSketch
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.hoyolab_account_relink_required

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

        // The credential migration unlinked accounts it could not carry over; they only come back
        // if the user pastes the cookies again, so say so rather than letting them fail silently.
        val snackbarController = LocalSnackbarController.current
        val relinkMessage = stringResource(Res.string.hoyolab_account_relink_required)
        LaunchedEffect(uiState.unlinkedHoYoLabAccounts) {
            if (uiState.unlinkedHoYoLabAccounts > 0) {
                snackbarController.showSnackbar(relinkMessage)
            }
        }

        if (!uiState.isLoading) {
            RootNavGraph()
        }
    }
}
