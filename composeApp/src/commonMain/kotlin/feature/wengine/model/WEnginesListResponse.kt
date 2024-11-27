/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WEnginesListResponse(
    @SerialName("w-engines")
    val wEngines: List<WEnginesListItemResponse>
)

@Serializable
data class WEnginesListItemResponse(
    val id: Int,
    val name: String,
    @SerialName("is_leak")
    val isLeak: Boolean,
    val rarity: Int,
    val specialty: String
)

val stubWEnginesListResponse = WEnginesListResponse(
    wEngines = listOf(
        WEnginesListItemResponse(
            id = 44,
            name = "好鬥的阿炮",
            isLeak = false,
            rarity = 4,
            specialty = "support",
        ),
        WEnginesListItemResponse(
            id = 47,
            name = "玉壺青冰",
            isLeak = false,
            rarity = 5,
            specialty = "stun",
        )
    )
)
