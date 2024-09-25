/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import utils.ZzzArchiveRarity

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
    @SerialName("img_profile")
    val imgProfile: String,
    @SerialName("img_portrait")
    val imgPortrait: String,
    @SerialName("faction_id")
    val factionId: Int
) {
    fun getRarity(): ZzzArchiveRarity {
        return when (rarity) {
            1 -> ZzzArchiveRarity.One
            2 -> ZzzArchiveRarity.Two
            3 -> ZzzArchiveRarity.Three
            4 -> ZzzArchiveRarity.Four
            5 -> ZzzArchiveRarity.Five
            else -> {
                ZzzArchiveRarity.One
            }
        }
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
            imgProfile = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Agent/Profile/3.webp",
            imgPortrait = "",
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
            imgProfile = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Agent/Profile/4.webp",
            imgPortrait = "",
            factionId = 1
        )
    )
)