/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.setting.model.SettingState
import ui.theme.AppTheme
import ui.utils.contentPadding

@Composable
fun SettingScreenSingle(
    uiState: SettingState,
    onLanguageChange: (String) -> Unit,
    onColorChange: (Boolean) -> Unit,
    onFeedbackClick: () -> Unit,
    onRestart: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
            .contentPadding(),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
    ) {
        SettingCard(
            uiState = uiState,
            onLanguageChange = onLanguageChange,
            onColorChange = onColorChange,
            onRestart = onRestart
        )
        OtherInfoCard(onFeedbackClick = onFeedbackClick)
        LicenseCard(uiState.appVersion)
        ContributorsCard(uiState.contributors)
    }
}