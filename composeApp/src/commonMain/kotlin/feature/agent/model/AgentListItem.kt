/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.model

import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity

data class AgentListItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isHighlight: Boolean,
    val rarity: ZzzRarity,
    val specialty: AgentSpecialty,
    val attribute: AgentAttribute,
    val faction: Faction,
    val materialUrl: String,
    val weeklyMaterialUrl: String,
    val skillMaterialUrls: List<String>,
    val levelMaterialUrls: List<String>,
    val wEngineMaterialUrls: List<String>,
    val portraitCount: Int = 1
)

@Suppress("ktlint:standard:max-line-length")
val stubAgentsList =
    listOf(
        AgentListItem(
            id = 3,
            name = "貓又",
            imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Agent/Profile/3.webp",
            isHighlight = false,
            rarity = ZzzRarity.RARITY_S,
            specialty = AgentSpecialty.Attack,
            attribute = AgentAttribute.Physical,
            faction = Faction(1),
            materialUrl = "",
            weeklyMaterialUrl = "",
            skillMaterialUrls = listOf(""),
            levelMaterialUrls = listOf(""),
            wEngineMaterialUrls = listOf("")
        ),
        AgentListItem(
            id = 4,
            name = "安比",
            imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Agent/Profile/4.webp",
            isHighlight = false,
            rarity = ZzzRarity.RARITY_A,
            specialty = AgentSpecialty.Stun,
            attribute = AgentAttribute.Electric,
            faction = Faction(1),
            materialUrl = "",
            weeklyMaterialUrl = "",
            skillMaterialUrls = listOf(""),
            levelMaterialUrls = listOf(""),
            wEngineMaterialUrls = listOf("")
        ),
        AgentListItem(
            id = 16,
            name = "可琳",
            imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Agent/Profile/16.webp",
            isHighlight = false,
            rarity = ZzzRarity.RARITY_A,
            specialty = AgentSpecialty.Attack,
            attribute = AgentAttribute.Physical,
            faction = Faction(2),
            materialUrl = "",
            weeklyMaterialUrl = "",
            skillMaterialUrls = listOf(""),
            levelMaterialUrls = listOf(""),
            wEngineMaterialUrls = listOf("")
        )
    )
