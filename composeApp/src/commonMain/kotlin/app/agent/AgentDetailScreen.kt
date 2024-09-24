/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent

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
import ui.component.ZzzOutlineButton
import ui.theme.AppTheme

@Composable
fun AgentDetailScreen(onWEngineClick: (String) -> Unit = {}) {
    Text(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        text = "11號",
        textAlign = TextAlign.Center,
        style = AppTheme.typography.headlineMedium,
        color = AppTheme.colors.onSurface
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        ZzzOutlineButton(text = "推薦武器：硫磺石", onClick = { onWEngineClick("硫磺石") })
    }
}