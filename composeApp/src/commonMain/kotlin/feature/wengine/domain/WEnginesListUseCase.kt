/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.domain

import feature.wengine.data.WEngineRepository
import feature.wengine.model.WEngineListItem
import utils.AgentSpecialty
import utils.ZzzRarity


class WEnginesListUseCase(
    private val wEngineRepository: WEngineRepository
) {
    suspend fun invoke() = wEngineRepository.getWEnginesList().map { it.wEngines.reversed() }

    fun filterWEnginesList(
        wEnginesList: List<WEngineListItem>,
        selectedRarities: Set<ZzzRarity>,
        selectedSpecialties: Set<AgentSpecialty>,
    ): List<WEngineListItem> {
        return wEnginesList.filter { wEngine ->
            val matchRarity =
                selectedRarities.isEmpty() || selectedRarities.any { it.level == wEngine.rarity }
            val matchSpecialty =
                selectedSpecialties.isEmpty() || selectedSpecialties.any { it.name.lowercase() == wEngine.specialty }

            matchRarity && matchSpecialty
        }
    }
}