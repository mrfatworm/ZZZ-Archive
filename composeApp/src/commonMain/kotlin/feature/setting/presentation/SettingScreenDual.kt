/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.setting.components.ContributorsCard
import feature.setting.components.LicenseCard
import feature.setting.components.OtherInfoCard
import feature.setting.components.SettingCard
import feature.setting.model.SettingState
import ui.utils.containerGap
import ui.utils.contentGap
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding

@Composable
fun SettingScreenDual(
    uiState: SettingState, onAction: (SettingAction) -> Unit
) {
    Row(
        modifier = Modifier.padding(horizontalSafePadding()),
        horizontalArrangement = Arrangement.spacedBy(containerGap())
    ) {
        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .padding(verticalSafePadding()),
            verticalArrangement = Arrangement.spacedBy(contentGap())
        ) {
            SettingCard(uiState = uiState, onLanguageChange = {
                onAction(SettingAction.ChangeLanguage(it))
            }, onColorChange = {
                onAction(SettingAction.ChangeToDarkTheme(it))
            }, onScaleChange = { uiScale, fontScale ->
                onAction(SettingAction.ScaleUi(uiScale, fontScale))
            }, onHoYoLabClick = {
                onAction(SettingAction.ClickHoYoLab)
            }, onRestart = {
                onAction(SettingAction.RestartApp)
            })
            OtherInfoCard(onFeedbackClick = {
                onAction(SettingAction.ClickFeedback)
            })
            LicenseCard(uiState.appVersion)
        }

        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .padding(verticalSafePadding()),
            verticalArrangement = Arrangement.spacedBy(contentGap())
        ) {
            ContributorsCard(uiState.contributors)
        }
    }
}





