/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.domain

import feature.setting.domain.LanguageUseCase
import feature.wengine.data.repository.WEngineRepository
import feature.wengine.model.WEnginesListItem
import kotlinx.coroutines.flow.first
import utils.AgentSpecialty
import utils.ZzzRarity


class WEnginesListUseCase(
    private val wEngineRepository: WEngineRepository, private val languageUseCase: LanguageUseCase
) {
    suspend fun invoke() =
        wEngineRepository.getWEnginesList(languageUseCase.getLanguage().first().officialCode)

    suspend fun updateWEnginesList() = wEngineRepository.requestAndUpdateWEnginesListDB(
        languageUseCase.getLanguage().first().officialCode
    )

    fun filterWEnginesList(
        wEnginesList: List<WEnginesListItem>,
        selectedRarities: Set<ZzzRarity>,
        selectedSpecialties: Set<AgentSpecialty>,
    ): List<WEnginesListItem> {
        return wEnginesList.filter { wEngine ->
            val matchRarity =
                selectedRarities.isEmpty() || selectedRarities.any { it == wEngine.rarity }
            val matchSpecialty =
                selectedSpecialties.isEmpty() || selectedSpecialties.any { it == wEngine.specialty }

            matchRarity && matchSpecialty
        }
    }
}