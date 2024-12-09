/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.domain

import feature.wengine.data.repository.WEngineRepository

class WEngineDetailUseCase(private val wEngineRepository: WEngineRepository) {
    suspend fun invoke(id: Int) = wEngineRepository.getWEngineDetail(id)
}