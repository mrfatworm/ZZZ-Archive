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
    val showUid: Boolean = false,
    val uid: String = "",
    val isCustomImage: Boolean = false,
    val customImgUrl: String = "",
    val customImgAuthor: String = "",
    val hasBlurBackground: Boolean = false,
    val isImageEditMode: Boolean = false,
    val adjustMode: Boolean = false,
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
    properties = emptyList(),
    skills = emptyList(),
    equipPlanInfo = null
)
