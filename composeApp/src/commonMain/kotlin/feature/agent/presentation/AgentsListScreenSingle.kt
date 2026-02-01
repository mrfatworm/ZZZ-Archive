/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.agent.components.AgentsList
import feature.agent.model.AgentsListState
import ui.utils.horizontalSafePadding

@Composable
fun AgentsListScreenSingle(
    uiState: AgentsListState,
    onAction: (AgentsListAction) -> Unit
) {
    AgentsList(
        modifier = Modifier.fillMaxSize().padding(horizontalSafePadding()),
        uiState = uiState,
        onGalleryClick = { id, portraitCount -> onAction(AgentsListAction.ClickGallery(id, portraitCount)) }
    )
}
