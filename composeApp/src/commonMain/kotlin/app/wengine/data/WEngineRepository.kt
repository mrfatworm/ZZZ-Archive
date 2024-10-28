/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine.data

import app.wengine.model.WEngineDetailResponse
import app.wengine.model.WEnginesListResponse
import utils.ZzzResult

interface WEngineRepository {
    suspend fun getWEnginesList(): ZzzResult<WEnginesListResponse>
    suspend fun getWEngineDetail(id: Int): ZzzResult<WEngineDetailResponse>
}