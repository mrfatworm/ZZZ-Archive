/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.model

import feature.agent.model.NameAndValue
import utils.AgentSpecialty
import utils.ZzzRarity

data class WEngineDetailState(
    val wEngineDetail: WEngineDetail = emptyWEngineDetail,
    val isLoading: Boolean = false,
    val error: String? = null
)

val emptyWEngineDetail =
    WEngineDetail(
        id = 0,
        name = "",
        imageUrl = "",
        isLeak = false,
        rarity = ZzzRarity.RANK_D,
        specialty = AgentSpecialty.None,
        background = "",
        atk = 0,
        stat = NameAndValue(
            name = "N/A", value = "0"
        ),
        skill = "",
        levelMaterials = emptyList()
    )