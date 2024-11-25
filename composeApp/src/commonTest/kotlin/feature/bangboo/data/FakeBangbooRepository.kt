/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.data

import feature.bangboo.model.BangbooDetailResponse
import feature.bangboo.model.BangbooListResponse
import feature.bangboo.model.stubBangbooDetailResponse
import feature.bangboo.model.stubBangbooListResponse

class FakeBangbooRepository : BangbooRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getBangbooList(): Result<BangbooListResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubBangbooListResponse)
        }
    }

    override suspend fun getBangbooDetail(id: Int): Result<BangbooDetailResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubBangbooDetailResponse)
        }
    }
}