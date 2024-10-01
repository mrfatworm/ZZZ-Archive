/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.model

import com.mrfatworm.zzzarchive.ZzzConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import utils.AgentAttribute
import kotlin.enums.EnumEntries

@Serializable
data class AgentsListResponse(
    val agents: List<AgentListItem>
) {
    fun getAgentsNewToOld(): List<AgentListItem> {
        return agents.reversed()
    }
}

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
    fun getProfileUrl(path: String = ZzzConfig.ASSET_PATH): String {
        return "https://raw.githubusercontent.com/$path/Agent/Profile/$id.webp"
    }

    fun getAttributeEnum(agentAttribute: EnumEntries<AgentAttribute> = AgentAttribute.entries) =
        agentAttribute.find {
            it.name.lowercase() == attribute.lowercase()
        } ?: AgentAttribute.None
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
