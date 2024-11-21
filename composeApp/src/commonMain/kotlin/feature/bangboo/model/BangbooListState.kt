/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.model

import utils.AgentAttribute
import utils.ZzzRarity


data class BangbooListState(
    val bangbooList: List<BangbooListItem> = emptyList(),
    val selectedRarity: Set<ZzzRarity> = emptySet(),
    val selectedAttributes: Set<AgentAttribute> = emptySet()
)