/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.data

import feature.bangboo.model.BangbooDetailResponse
import feature.bangboo.model.BangbooListResponse
import feature.bangboo.model.stubBangbooDetailResponse
import feature.bangboo.model.stubBangbooListResponse
import utils.ZzzResult

class FakeBangbooRepository : BangbooRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getBangbooList(): ZzzResult<BangbooListResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubBangbooListResponse)
        }
    }

    override suspend fun getBangbooDetail(id: Int): ZzzResult<BangbooDetailResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubBangbooDetailResponse)
        }
    }
}