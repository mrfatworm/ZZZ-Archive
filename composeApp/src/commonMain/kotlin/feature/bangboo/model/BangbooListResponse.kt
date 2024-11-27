/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BangbooListResponse(
    val bangboo: List<BangbooListItemResponse>
)

@Serializable
data class BangbooListItemResponse(
    val id: Int,
    val name: String,
    @SerialName("is_leak") val isLeak: Boolean,
    val rarity: Int,
    val attribute: String
)

val stubBangbooListResponse = BangbooListResponse(
    bangboo = listOf(
        BangbooListItemResponse(
            id = 1,
            name = "企鵝布",
            isLeak = false,
            rarity = 4,
            attribute = "ice",
        ), BangbooListItemResponse(
            id = 2,
            name = "巴特勒",
            isLeak = false,
            rarity = 5,
            attribute = "physical",
        ), BangbooListItemResponse(
            id = 6,
            name = "共鳴布",
            isLeak = false,
            rarity = 5,
            attribute = "ether"
        )
    )
)
