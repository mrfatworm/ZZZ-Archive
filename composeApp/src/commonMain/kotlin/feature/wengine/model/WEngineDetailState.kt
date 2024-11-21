/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.model

import feature.agent.model.NameAndValue

data class WEngineDetailState(
    val wEngineDetail: WEngineDetailResponse = emptyWEngineDetailResponse
)

val emptyWEngineDetailResponse =
    WEngineDetailResponse(
        id = 0,
        name = "",
        isLeak = false,
        rarity = 5,
        specialty = "",
        background = "",
        atk = 0,
        stat = NameAndValue(
            name = "N/A", value = "0"
        ),
        skill = "",
        levelMaterials = emptyList()
    )