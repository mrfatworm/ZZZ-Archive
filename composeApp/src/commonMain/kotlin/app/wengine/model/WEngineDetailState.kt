/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine.model

import app.agent.model.NameAndValue

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