/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.data.repository

import feature.wengine.model.WEngineDetail
import feature.wengine.model.WEnginesListItem
import kotlinx.coroutines.flow.Flow

interface WEngineRepository {
    suspend fun getWEnginesList(): Flow<List<WEnginesListItem>>
    suspend fun requestAndUpdateWEnginesListDB(): Result<Unit>
    suspend fun getWEngineDetail(id: Int): Result<WEngineDetail>
}