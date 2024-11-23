/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import feature.setting.model.FeedbackIssueType
import feature.setting.model.FeedbackState
import feature.setting.model.feedbackIssueTypes
import org.jetbrains.compose.resources.stringResource
import ui.components.ZzzTopBar
import ui.components.buttons.ZzzPrimaryButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.feedback
import zzzarchive.composeapp.generated.resources.ic_arrow_up
import zzzarchive.composeapp.generated.resources.invalid_form
import zzzarchive.composeapp.generated.resources.submit_form

@Composable
fun FeedbackScreenCompact(
    uiState: FeedbackState,
    onFormSubmit: (FeedbackIssueType, String, String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        ZzzTopBar(
            title = stringResource(Res.string.feedback), onBackClick = onBackClick
        )

    }) { contentPadding ->
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
                .padding(contentPadding)
                .padding(AppTheme.dimens.paddingParentCompact),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            var issueTextFieldValue by remember { mutableStateOf("") }
            var nicknameTextFieldValue by remember { mutableStateOf("") }
            var selectedIssue by remember { mutableStateOf(feedbackIssueTypes.first()) }
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
            Spacer(modifier = Modifier.weight(1f))
            if (uiState.invalidForm) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.invalid_form),
                    textAlign = TextAlign.Center,
                    color = AppTheme.colors.alert,
                    style = AppTheme.typography.titleSmall
                )
            }
            ZzzPrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(Res.string.submit_form),
                iconRes = Res.drawable.ic_arrow_up
            ) {
                onFormSubmit(selectedIssue, issueTextFieldValue, nicknameTextFieldValue)
            }
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}