/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.data

import feature.wengine.model.WEngineDetailResponse
import feature.wengine.model.WEnginesListResponse
import feature.wengine.model.stubWEngineDetailResponse
import feature.wengine.model.stubWEnginesListResponse
import utils.ZzzResult

class FakeWEngineRepository : WEngineRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getWEnginesList(): ZzzResult<WEnginesListResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubWEnginesListResponse)
        }
    }

    override suspend fun getWEngineDetail(id: Int): ZzzResult<WEngineDetailResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubWEngineDetailResponse)
        }
    }
}