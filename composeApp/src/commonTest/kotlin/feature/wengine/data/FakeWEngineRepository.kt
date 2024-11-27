/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.data

import feature.wengine.data.repository.WEngineRepository
import feature.wengine.model.WEngineDetail
import feature.wengine.model.WEnginesListItem
import feature.wengine.model.stubWEngineDetailResponse
import feature.wengine.model.stubWEnginesListResponse
import kotlinx.coroutines.flow.Flow

class FakeWEngineRepository : WEngineRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getWEnginesList(): Flow<List<WEnginesListItem>> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubWEnginesListResponse)
        }
    }

    override suspend fun getWEngineDetail(id: Int): Result<WEngineDetail> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubWEngineDetailResponse)
        }
    }
}