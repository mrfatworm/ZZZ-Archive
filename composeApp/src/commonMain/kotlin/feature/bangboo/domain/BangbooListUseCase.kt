/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.domain

import feature.bangboo.data.repository.BangbooRepository
import feature.bangboo.model.BangbooListItem
import feature.setting.domain.LanguageUseCase
import kotlinx.coroutines.flow.first
import utils.AgentAttribute
import utils.ZzzRarity

class BangbooListUseCase(
    private val bangbooRepository: BangbooRepository,
    private val languageUseCase: LanguageUseCase
) {
    suspend fun invoke() =
        bangbooRepository.getBangbooList(languageUseCase.getLanguage().first().officialCode)

    suspend fun updateBangbooList() = bangbooRepository.requestAndUpdateBangbooListDB(
        languageUseCase.getLanguage().first().officialCode
    )

    fun filterBangbooList(
        bangbooList: List<BangbooListItem>,
        selectedRarities: Set<ZzzRarity>,
        selectedAttributes: Set<AgentAttribute>
    ): List<BangbooListItem> {
        return bangbooList.filter { bangboo ->
            val matchRarity =
                selectedRarities.isEmpty() || selectedRarities.any { it == bangboo.rarity }
            val matchAttribute =
                selectedAttributes.isEmpty() || selectedAttributes.any { it == bangboo.attribute }
            matchRarity && matchAttribute
        }
    }
}