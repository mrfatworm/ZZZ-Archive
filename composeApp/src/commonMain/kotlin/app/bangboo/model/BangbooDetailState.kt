/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.bangboo.model

import app.agent.model.AgentBasicData
import app.agent.model.NameAndDesc

data class BangbooDetailState(
    val bangbooDetail: BangbooDetailResponse = emptyBangbooDetailResponse
)

val emptyBangbooDetailResponse = BangbooDetailResponse(
    id = 0,
    name = "",
    isLeak = false,
    rarity = 5,
    attribute = "",
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
    )
)