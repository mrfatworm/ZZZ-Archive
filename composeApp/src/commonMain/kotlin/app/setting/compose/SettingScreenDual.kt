/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.setting.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.setting.model.SettingState
import ui.theme.AppTheme
import ui.utils.AdaptiveLayoutType
import ui.utils.contentPadding

@Composable
fun SettingScreenDual(
    uiState: SettingState,
    adaptiveLayoutType: AdaptiveLayoutType,
    onLanguageChange: (String) -> Unit,
    onColorChange: (Boolean) -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)) {
        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .contentPadding(adaptiveLayoutType, AppTheme.dimens),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
        ) {
            SettingCard(
                uiState = uiState,
                onLanguageChange = onLanguageChange,
                onColorChange = onColorChange
            )
            OtherInfoCard(onFeedbackClick = {})
            LicenseCard()
        }

        Column(
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                .contentPadding(adaptiveLayoutType, AppTheme.dimens),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.gapContentExpanded)
        ) {
            ContributorsCard(uiState.contributors)
        }
    }
}





