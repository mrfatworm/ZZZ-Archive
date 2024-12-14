/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.domain

import feature.setting.domain.LanguageUseCase
import feature.wengine.data.repository.WEngineRepository
import kotlinx.coroutines.flow.first

class WEngineDetailUseCase(
    private val wEngineRepository: WEngineRepository,
    private val languageUseCase: LanguageUseCase
) {
    suspend fun invoke(id: Int) =
        wEngineRepository.getWEngineDetail(id, languageUseCase.getLanguage().first().officialCode)
}