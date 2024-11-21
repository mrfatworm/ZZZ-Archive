/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.compose

import androidx.compose.runtime.Composable
import feature.agent.model.AgentDetailResponse
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.CardHeader
import ui.components.cards.ContentCard
import ui.components.items.ExpandableItem
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.mindscape_cinema

val CinemaTitle = listOf("I", "II", "III", "IV", "V", "VI")

@Composable
fun CinemaCard(agentDetail: AgentDetailResponse) {
    ContentCard(hasDefaultPadding = false) {
        CardHeader(
            title = stringResource(Res.string.mindscape_cinema)
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