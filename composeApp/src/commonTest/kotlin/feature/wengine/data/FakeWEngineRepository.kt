/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.data

import feature.wengine.model.WEngineDetailResponse
import feature.wengine.model.WEnginesListResponse
import feature.wengine.model.stubWEngineDetailResponse
import feature.wengine.model.stubWEnginesListResponse

class FakeWEngineRepository : WEngineRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getWEnginesList(): Result<WEnginesListResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubWEnginesListResponse)
        }
    }

    override suspend fun getWEngineDetail(id: Int): Result<WEngineDetailResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubWEngineDetailResponse)
        }
    }
}