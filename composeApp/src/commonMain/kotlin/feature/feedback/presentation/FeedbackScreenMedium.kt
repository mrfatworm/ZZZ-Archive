/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.feedback.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import feature.feedback.components.FeedbackFormCard
import feature.feedback.model.FeedbackState
import feature.feedback.model.feedbackIssueTypes
import org.jetbrains.compose.resources.stringResource
import ui.components.TopBarRound
import ui.components.buttons.ZzzPrimaryButton
import ui.theme.AppTheme
import ui.utils.contentGap
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.feedback
import zzzarchive.composeapp.generated.resources.ic_arrow_up
import zzzarchive.composeapp.generated.resources.invalid_feedback_form
import zzzarchive.composeapp.generated.resources.submit_form

@Composable
fun FeedbackScreenMedium(
    uiState: FeedbackState,
    onAction: (FeedbackAction) -> Unit
) {
    var issueTextFieldValue by remember { mutableStateOf("") }
    var nicknameTextFieldValue by remember { mutableStateOf("") }
    var selectedIssue by remember { mutableStateOf(feedbackIssueTypes.first()) }
    Column(
        modifier = Modifier.widthIn(max = 640.dp)
            .verticalScroll(rememberScrollState())
            .padding(horizontalSafePadding())
            .padding(verticalSafePadding()),
        verticalArrangement = Arrangement.spacedBy(contentGap())
    ) {
        TopBarRound(title = stringResource(Res.string.feedback), onBackClick = {
            onAction(FeedbackAction.ClickBack)
        })

        FeedbackFormCard(uiState,
            issueTextFieldValue,
            nicknameTextFieldValue,
            onIssueDescChange = {
                issueTextFieldValue = it
            },
            onNickNameChange = {
                nicknameTextFieldValue = it
            },
            onIssueSelected = {
                selectedIssue = it
            })

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
            onAction(
                FeedbackAction.SubmitForm(
                    selectedIssue,
                    issueTextFieldValue,
                    nicknameTextFieldValue
                )
            )
        }
    }

}