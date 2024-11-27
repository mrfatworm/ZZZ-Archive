/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.model

import utils.AgentSpecialty
import utils.ZzzRarity


data class WEnginesListState(
    val wEnginesList: List<WEnginesListItem> = emptyList(),
    val filteredWEnginesList: List<WEnginesListItem> = emptyList(),
    val selectedRarity: Set<ZzzRarity> = emptySet(),
    val selectedSpecialties: Set<AgentSpecialty> = emptySet(),
    val error: String? = null
)