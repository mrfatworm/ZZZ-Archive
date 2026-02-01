/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgentsListResponse(val agents: List<AgentListItemResponse>)

@Serializable
data class AgentListItemResponse(
    val id: Int? = null,
    val name: String? = null,
    @SerialName("is_highlight")
    val isHighlight: Boolean? = null,
    val rarity: Int? = null,
    val specialty: String? = null,
    val attribute: String? = null,
    @SerialName("faction_id")
    val factionId: Int? = null,
    val material: Int? = null,
    @SerialName("weekly_material")
    val weeklyMaterial: Int? = null,
    @SerialName("portrait_count")
    val portraitCount: Int? = null
)

val stubAgentsListResponse =
    AgentsListResponse(
        agents =
            listOf(
                AgentListItemResponse(
                    id = 3,
                    name = "貓又",
                    isHighlight = false,
                    rarity = 5,
                    specialty = "attack",
                    attribute = "physical",
                    factionId = 1
                ),
                AgentListItemResponse(
                    id = 4,
                    name = "安比",
                    isHighlight = false,
                    rarity = 4,
                    specialty = "stun",
                    attribute = "electric",
                    factionId = 1
                ),
                AgentListItemResponse(
                    id = 16,
                    name = "可琳",
                    isHighlight = false,
                    rarity = 4,
                    specialty = "attack",
                    attribute = "physical",
                    factionId = 2
                )
            )
    )
