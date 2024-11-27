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

val stubWEnginesListItem = WEnginesListItem(
    id = 44,
    name = "好鬥的阿炮",
    imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset//W-Engine/Image/44.webp",
    isLeak = false,
    rarity = ZzzRarity.RANK_A,
    specialty = AgentSpecialty.Support,
)