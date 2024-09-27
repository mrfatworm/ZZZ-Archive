/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.model

import com.mrfatworm.zzzarchive.ZzzConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import utils.ZzzRarity

@Serializable
data class AgentsListResponse(
    val agents: List<AgentListItem>
)

@Serializable
data class AgentListItem(
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
) {
    fun getProfileUrl(): String {
        return "https://raw.githubusercontent.com/${ZzzConfig.ASSET_PATH}/Agent/Profile/$id.webp"
    }
}

val stubAgentsListResponse = AgentsListResponse(
    agents = listOf(
        AgentListItem(
            id = 3,
            name = "貓又",
            fullName = "貓宮 又奈",
            isLeak = false,
            rarity = 5,
            specialty = "strike",
            attribute = "physical",
            attackType = "slash",
            factionId = 1
        ),
        AgentListItem(
            id = 4,
            name = "安比",
            fullName = "安比·德瑪拉",
            isLeak = false,
            rarity = 4,
            specialty = "stun",
            attribute = "electric",
            attackType = "slash",
            factionId = 1
        )
    )
)
