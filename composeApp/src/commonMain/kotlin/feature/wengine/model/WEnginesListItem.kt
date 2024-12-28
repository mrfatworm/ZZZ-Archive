/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.model

import utils.AgentSpecialty
import utils.ZzzRarity

data class WEnginesListItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isLeak: Boolean,
    val rarity: ZzzRarity,
    val specialty: AgentSpecialty
)

val stubWEnginesList = listOf(
    WEnginesListItem(
        id = 44,
        name = "好鬥的阿炮",
        imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset//W-Engine/Image/44.webp",
        isLeak = false,
        rarity = ZzzRarity.RARITY_A,
        specialty = AgentSpecialty.Support,
    ),
    WEnginesListItem(
        id = 45,
        name = "玉壺青冰",
        imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset//W-Engine/Image/45.webp",
        isLeak = false,
        rarity = ZzzRarity.RARITY_S,
        specialty = AgentSpecialty.Stun,
    )
)