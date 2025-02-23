/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model

import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity

data class MyAgentListItem(
    val id: Int,
    val name: String,
    val level: Int,
    val rank: Int,
    val imageUrl: String,
    val rarity: ZzzRarity,
    val specialty: AgentSpecialty,
    val attribute: AgentAttribute,
)

val stubMyAgentsList = listOf(
    MyAgentListItem(
        id = 1311,
        name = "Astra Yao",
        level = 60,
        rank = 1,
        imageUrl = "https://act-webstatic.hoyoverse.com/game_record/zzzv2/role_square_avatar/role_square_avatar_1311_3113111.png",
        rarity = ZzzRarity.RARITY_S,
        specialty = AgentSpecialty.Support,
        attribute = AgentAttribute.Ether,
    ),
    MyAgentListItem(
        id = 1251,
        name = "Qingyi",
        level = 60,
        rank = 1,
        imageUrl = "https://act-webstatic.hoyoverse.com/game_record/zzzv2/role_square_avatar/role_square_avatar_1251.png",
        rarity = ZzzRarity.RARITY_S,
        specialty = AgentSpecialty.Stun,
        attribute = AgentAttribute.Electric,
    )
)