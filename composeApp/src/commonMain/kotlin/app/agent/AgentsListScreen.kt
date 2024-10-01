/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent

import androidx.compose.runtime.Composable
import app.agent.model.stubAgentsListState
import ui.utils.ContentType

@Composable
fun AgentsListScreen(
    contentType: ContentType, onAgentClick: (Int) -> Unit = {}
) {
    if (contentType == ContentType.Single) {
        AgentsListScreenSingle(
            state = stubAgentsListState, onAgentClick = onAgentClick
        )
    } else {
        AgentsListScreenDual(
            state = stubAgentsListState, onAgentClick = onAgentClick
        )
    }
}