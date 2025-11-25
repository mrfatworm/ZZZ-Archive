/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.feedback.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import feature.feedback.components.FeedbackFormCard
import feature.feedback.model.FeedbackState
import org.jetbrains.compose.resources.stringResource
import ui.components.TopBarScaffold
import ui.components.buttons.ZzzPrimaryButton
import ui.theme.AppTheme
import ui.utils.contentPaddingInScaffold
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.feedback
import zzzarchive.composeapp.generated.resources.ic_arrow_up
import zzzarchive.composeapp.generated.resources.invalid_feedback_form
import zzzarchive.composeapp.generated.resources.submit_form

@Composable
fun FeedbackScreenCompact(
    uiState: FeedbackState,
    onAction: (FeedbackAction) -> Unit
) {
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        TopBarScaffold(
            title = stringResource(Res.string.feedback),
            onBackClick = {
                onAction(FeedbackAction.ClickBack)
            }
        )
    }) { scaffoldPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .contentPaddingInScaffold(scaffoldPadding),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s350)
        ) {
            FeedbackFormCard(
                feedbackState = uiState,
                onIssueSelected = {
                    onAction(FeedbackAction.OnSelectedIssueChange(it))
                },
                onDescChanged = {
                    onAction(FeedbackAction.OnDescTextFieldChange(it))
                },
                onEmailChanged = {
                    onAction(FeedbackAction.OnEmailTextFieldChange(it))
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            if (uiState.invalidForm) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.invalid_feedback_form),
                    textAlign = TextAlign.Center,
                    color = AppTheme.colors.alert,
                    style = AppTheme.typography.titleSmall
                )
            }

            ZzzPrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(Res.string.submit_form),
                iconRes = Res.drawable.ic_arrow_up,
                enabled = !uiState.isLoading
            ) {
                onAction(FeedbackAction.SubmitForm)
            }

            Spacer(Modifier.size(AppTheme.spacing.s300))
        }
    }
}
