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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.agent.domain.AgentDetailViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import ui.component.ZzzOutlineButton
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.loading

@Composable
fun AgentDetailScreen() {
    val viewModel: AgentDetailViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val agentDetail = uiState.agentDetail
    if (agentDetail == null) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            text = stringResource(Res.string.loading),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.headlineMedium,
            color = AppTheme.colors.onSurface
        )
    } else {
        Text(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            text = agentDetail.fullName,
            textAlign = TextAlign.Center,
            style = AppTheme.typography.headlineMedium,
            color = AppTheme.colors.onSurface
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            ZzzOutlineButton(text = "推薦武器：硫磺石", onClick = { })
        }
    }

}