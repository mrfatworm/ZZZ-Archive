/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.data

import feature.bangboo.data.repository.BangbooRepository
import feature.bangboo.model.BangbooDetail
import feature.bangboo.model.BangbooListItem
import feature.bangboo.model.stubBangbooDetailResponse
import feature.bangboo.model.stubBangbooListResponse
import kotlinx.coroutines.flow.Flow

class FakeBangbooRepository : BangbooRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getBangbooList(): Flow<List<BangbooListItem>> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubBangbooListResponse)
        }
    }

    override suspend fun getBangbooDetail(id: Int): Result<BangbooDetail> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubBangbooDetailResponse)
        }
    }
}