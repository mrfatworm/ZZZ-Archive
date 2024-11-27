/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.model

import feature.agent.model.AgentBasicData
import feature.agent.model.NameAndDesc
import utils.AgentAttribute
import utils.ZzzRarity

data class BangbooDetailState(
    val bangbooDetail: BangbooDetail = emptyBangbooDetail,
    val isLoading: Boolean = false,
    val error: String? = null
)

val emptyBangbooDetail = BangbooDetail(
    id = 0,
    name = "",
    imageUrl = "",
    isLeak = false,
    rarity = ZzzRarity.RANK_D,
    attribute = AgentAttribute.None,
    basicData = AgentBasicData(
        hp = 0, atk = 0, def = 0
    ),
    activeSkill = NameAndDesc(
        name = "", description = ""
    ),
    additionalAbility = NameAndDesc(
        name = "", description = ""
    ),
    chainAttack = NameAndDesc(
        name = "", description = ""
    ),
    levelMaterials = emptyList()
)