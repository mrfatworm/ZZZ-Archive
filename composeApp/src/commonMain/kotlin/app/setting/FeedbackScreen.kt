/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: MIT License
 */

package app.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.setting.compose.FeedbackScreenCompact
import app.setting.compose.FeedbackScreenMedium
import app.setting.domain.FeedbackViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ui.components.dialogs.ConfirmDialog
import ui.utils.AdaptiveLayoutType
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.form_submit_success

@Composable
fun FeedbackScreen(adaptiveLayoutType: AdaptiveLayoutType, onBackClick: () -> Unit) {
    val viewModel: FeedbackViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (adaptiveLayoutType == AdaptiveLayoutType.Compact) {
            FeedbackScreenCompact(
                uiState.value, onFormSubmit = { issueIndex, issueContent, nickname ->
                    viewModel.submitFeedback(issueIndex, issueContent, nickname)
                }, onBackClick = onBackClick
            )
        } else {
            FeedbackScreenMedium(
                uiState.value,
                adaptiveLayoutType,
                onFormSubmit = { issueIndex, issueContent, nickname ->
                    viewModel.submitFeedback(issueIndex, issueContent, nickname)
                },
                onBackClick = onBackClick
            )
        }
    }
    when {
        uiState.value.showSubmitSuccessDialog -> {
            ConfirmDialog(stringResource(Res.string.form_submit_success), onDismiss = {
                viewModel.dismissSubmitSuccessDialog()
            })
        }
    }
}
