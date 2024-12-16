/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.feedback.presentation

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import feature.feedback.model.FeedbackState
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ui.components.dialogs.SingleActionDialog
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.form_submit_success

@Composable
fun FeedbackScreen(onBackClick: () -> Unit) {
    val viewModel: FeedbackViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxSize().pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FeedbackScreenContent(uiState, onAction = { actions ->
            when (actions) {
                FeedbackAction.ClickBack -> onBackClick()
                else -> viewModel.onAction(actions)
            }
        })
    }
}

@Composable
private fun FeedbackScreenContent(
    uiState: FeedbackState,
    onAction: (FeedbackAction) -> Unit
) {
    if (AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
        FeedbackScreenCompact(uiState, onAction)
    } else {
        FeedbackScreenMedium(uiState, onAction)
    }
    when {
        uiState.showSubmitSuccessDialog -> {
            SingleActionDialog(stringResource(Res.string.form_submit_success), onAction = {
                onAction(FeedbackAction.DismissDialog)
            }, onDismiss = {
                onAction(FeedbackAction.DismissDialog)
            })
        }
    }
}
