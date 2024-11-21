/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.data

import feature.wengine.model.WEngineDetailResponse
import feature.wengine.model.WEnginesListResponse
import utils.ZzzResult

interface WEngineRepository {
    suspend fun getWEnginesList(): ZzzResult<WEnginesListResponse>
    suspend fun getWEngineDetail(id: Int): ZzzResult<WEngineDetailResponse>
}