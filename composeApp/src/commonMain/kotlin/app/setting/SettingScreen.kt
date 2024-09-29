/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ui.component.ZzzOutlineButton
import ui.theme.AppTheme
import ui.utils.ContentType

@Composable
fun SettingScreen(
    contentType: ContentType, onFeedbackClicked: () -> Unit
) {
    if (contentType == ContentType.Single) {
        SettingScreenSingle(onFeedbackClicked)
    } else {
        SettingScreenDual()
    }
}

@Composable
fun SettingScreenSingle(
    onFeedbackClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            16.dp, alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = "Setting",
            textAlign = TextAlign.Center,
            style = AppTheme.typography.headlineMedium,
            color = AppTheme.colors.onSurface
        )
        ZzzOutlineButton(text = "Feedback", onClick = { onFeedbackClicked() })
    }
}

@Composable
fun SettingScreenDual() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            16.dp, alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = "Setting",
            textAlign = TextAlign.Center,
            style = AppTheme.typography.headlineMedium,
            color = AppTheme.colors.onSurface
        )
    }
}