/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgentsListResponse(
    val agents: List<AgentListItemResponse>
)

@Serializable
data class AgentListItemResponse(
    val id: Int,
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("is_leak")
    val isLeak: Boolean,
    val rarity: Int,
    val specialty: String,
    val attribute: String,
    @SerialName("attack_type")
    val attackType: String,
    @SerialName("faction_id")
    val factionId: Int
)

val stubAgentsListResponse = AgentsListResponse(
    agents = listOf(
        AgentListItemResponse(
            id = 3,
            name = "貓又",
            fullName = "貓宮 又奈",
            isLeak = false,
            rarity = 5,
            specialty = "attack",
            attribute = "physical",
            attackType = "slash",
            factionId = 1
        ),
        AgentListItemResponse(
            id = 4,
            name = "安比",
            fullName = "安比·德瑪拉",
            isLeak = false,
            rarity = 4,
            specialty = "stun",
            attribute = "electric",
            attackType = "slash",
            factionId = 1
        ),
        AgentListItemResponse(
            id = 16,
            name = "可琳",
            fullName = "可琳·威克斯",
            isLeak = false,
            rarity = 4,
            specialty = "attack",
            attribute = "physical",
            attackType = "slash",
            factionId = 2
        )
    )
)
