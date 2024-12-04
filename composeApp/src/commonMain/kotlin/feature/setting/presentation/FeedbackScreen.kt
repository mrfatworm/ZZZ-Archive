/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import feature.setting.components.FeedbackScreenCompact
import feature.setting.components.FeedbackScreenMedium
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ui.components.dialogs.ConfirmDialog
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.form_submit_success

@Composable
fun FeedbackScreen(onBackClick: () -> Unit) {
    val viewModel: FeedbackViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.fillMaxSize().pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (AppTheme.adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            FeedbackScreenCompact(
                uiState.value, onFormSubmit = { issueIndex, issueContent, nickname ->
                    viewModel.submitFeedback(issueIndex, issueContent, nickname)
                }, onBackClick = onBackClick
            )
        } else {
            FeedbackScreenMedium(
                uiState.value, onFormSubmit = { issueIndex, issueContent, nickname ->
                    viewModel.submitFeedback(issueIndex, issueContent, nickname)
                }, onBackClick = onBackClick
            )
        }
    }
    when {
        uiState.value.showSubmitSuccessDialog -> {
            ConfirmDialog(stringResource(Res.string.form_submit_success), onAction = {
                viewModel.dismissSubmitSuccessDialog()
            }, onDismiss = {
                viewModel.dismissSubmitSuccessDialog()
            })
        }
    }
}
