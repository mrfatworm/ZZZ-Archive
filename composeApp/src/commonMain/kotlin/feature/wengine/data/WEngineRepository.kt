/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.data

import feature.wengine.model.WEngineDetailResponse
import feature.wengine.model.WEnginesListResponse

interface WEngineRepository {
    suspend fun getWEnginesList(): Result<WEnginesListResponse>
    suspend fun getWEngineDetail(id: Int): Result<WEngineDetailResponse>
}