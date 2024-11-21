/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.model

import utils.AgentSpecialty
import utils.ZzzRarity


data class WEnginesListState(
    val wEnginesList: List<WEngineListItem> = emptyList(),
    val selectedRarity: Set<ZzzRarity> = emptySet(),
    val selectedSpecialties: Set<AgentSpecialty> = emptySet()
)