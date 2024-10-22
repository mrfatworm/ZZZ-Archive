/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.bangboo.model

import utils.AgentAttribute
import utils.ZzzRarity


data class BangbooListState(
    val bangbooList: List<BangbooListItem> = emptyList(),
    val selectedRarity: Set<ZzzRarity> = emptySet(),
    val selectedAttributes: Set<AgentAttribute> = emptySet()
)