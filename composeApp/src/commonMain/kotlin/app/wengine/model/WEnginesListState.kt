/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine.model

import utils.AgentSpecialty
import utils.ZzzRarity


data class WEnginesListState(
    val wEnginesList: List<WEngineListItem> = emptyList(),
    val selectedRarity: Set<ZzzRarity> = emptySet(),
    val selectedSpecialties: Set<AgentSpecialty> = emptySet()
)