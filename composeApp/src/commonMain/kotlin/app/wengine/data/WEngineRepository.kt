/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.wengine.data

import app.wengine.model.WEngineDetailResponse
import app.wengine.model.WEnginesListResponse
import utils.ZzzResult

interface WEngineRepository {
    suspend fun getWEnginesList(): ZzzResult<WEnginesListResponse>
    suspend fun getWEngineDetail(id: Int): ZzzResult<WEngineDetailResponse>
}