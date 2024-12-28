/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.model

import utils.AgentAttackType
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity

data class AgentListItem(
    val id: Int,
    val name: String,
    val fullName: String,
    val imageUrl: String,
    val isLeak: Boolean,
    val rarity: ZzzRarity,
    val specialty: AgentSpecialty,
    val attribute: AgentAttribute,
    val attackType: AgentAttackType,
    val factionId: Int
)

val stubAgentsList = listOf(
    AgentListItem(
        id = 3,
        name = "貓又",
        fullName = "貓宮 又奈",
        imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Agent/Profile/3.webp",
        isLeak = false,
        rarity = ZzzRarity.RARITY_S,
        specialty = AgentSpecialty.Attack,
        attribute = AgentAttribute.Physical,
        attackType = AgentAttackType.Slash,
        factionId = 1
    ),
    AgentListItem(
        id = 4,
        name = "安比",
        fullName = "安比·德瑪拉",
        imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Agent/Profile/4.webp",
        isLeak = false,
        rarity = ZzzRarity.RARITY_A,
        specialty = AgentSpecialty.Stun,
        attribute = AgentAttribute.Electric,
        attackType = AgentAttackType.Slash,
        factionId = 1
    ),
    AgentListItem(
        id = 16,
        name = "可琳",
        fullName = "可琳·威克斯",
        imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Agent/Profile/16.webp",
        isLeak = false,
        rarity = ZzzRarity.RARITY_A,
        specialty = AgentSpecialty.Attack,
        attribute = AgentAttribute.Physical,
        attackType = AgentAttackType.Slash,
        factionId = 2
    )
)