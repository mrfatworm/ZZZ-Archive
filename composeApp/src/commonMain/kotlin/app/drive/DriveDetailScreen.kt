/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.drive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.components.buttons.ZzzOutlineButton
import ui.theme.AppTheme
import ui.theme.ZzzArchiveTheme

@Composable
fun DriveDetailScreen(onAgentClick: (String) -> Unit = {}) {
    Text(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        text = "震星迪斯可",
        textAlign = TextAlign.Center,
        style = AppTheme.typography.headlineMedium,
        color = AppTheme.colors.onSurface
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        ZzzOutlineButton(text = "推薦角色：11號", onClick = { onAgentClick("11號") })
    }
}

@Preview
@Composable
fun PreviewDriveDetailScreen() {
    ZzzArchiveTheme {
        DriveDetailScreen()
    }
}