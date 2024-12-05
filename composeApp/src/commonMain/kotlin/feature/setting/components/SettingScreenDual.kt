/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.setting.model.SettingState
import feature.setting.presentation.SettingAction
import ui.theme.AppTheme
import ui.utils.contentPadding

@Composable
fun SettingScreenDual(
    uiState: SettingState,
    onAction: (SettingAction) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)) {
        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .contentPadding(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
        ) {
            SettingCard(
                uiState = uiState,
                onLanguageChange = {
                    onAction(SettingAction.ChangeLanguage(it))
                },
                onColorChange = {
                    onAction(SettingAction.ChangeToDarkTheme(it))
                },
                onScaleChange = { uiScale, fontScale ->
                    onAction(SettingAction.ScaleUi(uiScale, fontScale))
                },
                onRestart = {
                    onAction(SettingAction.RestartApp)
                }
            )
            OtherInfoCard(onFeedbackClick = {
                onAction(SettingAction.ClickFeedback)
            })
            LicenseCard(uiState.appVersion)
        }

        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .contentPadding(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
        ) {
            ContributorsCard(uiState.contributors)
        }
    }
}





