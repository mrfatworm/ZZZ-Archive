/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.model

import utils.AgentAttribute
import utils.ZzzRarity

data class BangbooListItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isLeak: Boolean,
    val rarity: ZzzRarity,
    val attribute: AgentAttribute
)

val stubBangbooList = listOf(
    BangbooListItem(
        id = 1,
        name = "企鵝布",
        imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Bangboo/Profile/1.webp",
        isLeak = false,
        rarity = ZzzRarity.RANK_A,
        attribute = AgentAttribute.Ice
    ), BangbooListItem(
        id = 2,
        name = "巴特勒",
        imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Bangboo/Profile/2.webp",
        isLeak = false,
        rarity = ZzzRarity.RANK_S,
        attribute = AgentAttribute.Physical
    )
)
