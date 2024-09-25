/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wiki

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.AppTheme
import ui.theme.ZzzArchiveTheme

@Composable
fun WikiScreenDual(
    onAgentsOverviewClick: () -> Unit = {},
    onWEnginesOverviewClick: () -> Unit = {},
    onDrivesOverviewClick: () -> Unit = {},
    onAgentDetailClick: (Int) -> Unit = {},
    onWEngineDetailClick: (Int) -> Unit = {},
    onDriveDetailClick: (Int) -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Wiki Dual",
            style = AppTheme.typography.headlineMedium,
            color = AppTheme.colors.onSurface
        )
    }
}


@Preview
@Composable
fun PreviewWikiScreenDual() {
    ZzzArchiveTheme {
        WikiScreenDual()
    }
}