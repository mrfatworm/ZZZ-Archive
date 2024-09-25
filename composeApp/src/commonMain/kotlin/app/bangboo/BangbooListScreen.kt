/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.bangboo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.component.ZzzOutlineButton
import ui.theme.AppTheme
import ui.theme.ZzzArchiveTheme

@Composable
fun BangbooListScreen(onBangbooClick: (String) -> Unit = {}) {
    Text(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        text = "邦布清單",
        textAlign = TextAlign.Center,
        style = AppTheme.typography.headlineMedium,
        color = AppTheme.colors.onSurface
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        ZzzOutlineButton(text = "破抹布", onClick = { onBangbooClick("破抹布") })
    }
}

@Preview
@Composable
fun PreviewBangbooListScreen() {
    ZzzArchiveTheme {
        BangbooListScreen()
    }
}