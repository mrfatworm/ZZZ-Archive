/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.agent.model.AgentDetailResponse
import org.jetbrains.compose.resources.stringResource
import ui.component.CardHeader
import ui.component.ContentCard
import ui.component.ExpandableItem
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.mindscape_cinema

val CinemaTitle = listOf("I", "II", "III", "IV", "V", "VI")

@Composable
fun CinemaCard(agentDetail: AgentDetailResponse) {
    ContentCard(hasDefaultPadding = false) {
        CardHeader(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(Res.string.mindscape_cinema).uppercase()
        )
        for (i in agentDetail.mindscapeCinema.indices) {
            ExpandableItem(
                title = CinemaTitle[i],
                subtitle = agentDetail.mindscapeCinema[i].name,
                description = agentDetail.mindscapeCinema[i].description
            )
        }
    }
}