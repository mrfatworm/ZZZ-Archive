/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import feature.setting.model.FeedbackIssueType
import feature.setting.model.FeedbackState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.ZzzTextFiled
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.app_version
import zzzarchive.composeapp.generated.resources.device_name
import zzzarchive.composeapp.generated.resources.ic_arrow_down_ios
import zzzarchive.composeapp.generated.resources.input_your_issue
import zzzarchive.composeapp.generated.resources.issue_type
import zzzarchive.composeapp.generated.resources.operating_system
import zzzarchive.composeapp.generated.resources.please_select
import zzzarchive.composeapp.generated.resources.your_nickname_optional

@Composable
fun FeedbackFormCard(
    feedbackState: FeedbackState,
    issueText: String,
    nicknameText: String,
    onIssueDescChange: (String) -> Unit,
    onNickNameChange: (String) -> Unit,
    onIssueSelected: (FeedbackIssueType) -> Unit
) {
    Column(
        modifier = Modifier.background(
            AppTheme.colors.surfaceContainer, AppTheme.shape.r400
        ).padding(horizontal = AppTheme.spacing.s200, vertical = AppTheme.spacing.s400)
    ) {
        IssueTypeItem(
            feedbackIssueTypes = feedbackState.issueTypes,
            onIssueSelected = onIssueSelected
        )
        IssueTextField(issueText, onIssueDescChange, nicknameText, onNickNameChange)
        SettingItemText(
            title = stringResource(Res.string.app_version), content = feedbackState.appVersion
        )
        SettingItemText(
            title = stringResource(Res.string.device_name), content = feedbackState.deviceName
        )
        SettingItemText(
            title = stringResource(Res.string.operating_system),
            content = feedbackState.operatingSystem
        )
    }

}

@Composable
private fun IssueTextField(
    issueText: String,
    onIssueChange: (String) -> Unit,
    nicknameText: String,
    onNickNameChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(
            horizontal = AppTheme.spacing.s400,
            vertical = AppTheme.spacing.s350
        ),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)
    ) {
        ZzzTextFiled(
            modifier = Modifier.fillMaxWidth(),
            hint = stringResource(Res.string.input_your_issue),
            issueText,
            onValueChange = onIssueChange,
            minLines = 8,
            maxLines = 16
        )
        ZzzTextFiled(
            modifier = Modifier.fillMaxWidth(),
            hint = stringResource(Res.string.your_nickname_optional),
            nicknameText,
            onValueChange = onNickNameChange,
            maxLines = 1
        )
    }
}

@Composable
private fun IssueTypeItem(
    feedbackIssueTypes: List<FeedbackIssueType>,
    onIssueSelected: (FeedbackIssueType) -> Unit
) {
    var selectedIssueType by remember { mutableStateOf(Res.string.please_select) }
    var showIssueTypesDropdown by remember { mutableStateOf(false) }
    SettingItem(title = stringResource(Res.string.issue_type), content = {
        Column(horizontalAlignment = Alignment.End) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
            ) {
                Text(
                    text = stringResource(selectedIssueType),
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colors.onSurface
                )
                Icon(
                    modifier = Modifier.size(AppTheme.size.smallIconSize),
                    imageVector = vectorResource(Res.drawable.ic_arrow_down_ios),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurfaceVariant
                )
            }
            DropdownMenu(expanded = showIssueTypesDropdown,
                containerColor = AppTheme.colors.surface,
                onDismissRequest = { showIssueTypesDropdown = false }) {
                feedbackIssueTypes.forEach { issueType ->
                    DropdownMenuItem(text = {
                        Text(
                            text = stringResource(issueType.localStringRes),
                            style = AppTheme.typography.labelMedium,
                            color = AppTheme.colors.onSurface
                        )
                    }, onClick = {
                        selectedIssueType = issueType.localStringRes
                        onIssueSelected(issueType)
                        showIssueTypesDropdown = false
                    })
                }
            }
        }
    }, onClick = {
        showIssueTypesDropdown = true
    })
}

@Composable
private fun SettingItemText(title: String, content: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s350),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = AppTheme.typography.titleMedium,
            color = AppTheme.colors.onSurfaceVariant
        )
        Text(
            text = content,
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colors.onSurface
        )
    }
}