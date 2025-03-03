/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.my_agent_detail

import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity

data class MyAgentDetailState(
    val agentDetail: MyAgentDetailListItem = emptyMyAgentDetailListItem,
    val score: String = "",
    val errorMessage: String = ""
)

val emptyMyAgentDetailListItem = MyAgentDetailListItem(
    id = 0,
    name = "",
    level = 0,
    rank = 0,
    imageUrl = "",
    groupImageUrl = "",
    rarity = ZzzRarity.RARITY_D,
    specialty = AgentSpecialty.None,
    attribute = AgentAttribute.None,
    equip = emptyList(),
    weapon = emptyMyAgentDetailWeaponResponse,
    properties = emptyList()
)
