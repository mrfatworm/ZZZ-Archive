/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.domain

import feature.bangboo.data.repository.BangbooRepository
import feature.setting.domain.LanguageUseCase
import kotlinx.coroutines.flow.first

class BangbooDetailUseCase(
    private val bangbooRepository: BangbooRepository,
    private val languageUseCase: LanguageUseCase
) {
    suspend fun invoke(id: Int) =
        bangbooRepository.getBangbooDetail(id, languageUseCase.getLanguage().first().officialCode)
}