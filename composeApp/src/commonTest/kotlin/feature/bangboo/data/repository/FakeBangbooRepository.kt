/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.data.repository

import feature.bangboo.model.BangbooDetail
import feature.bangboo.model.BangbooListItem
import feature.bangboo.model.stubBangbooDetail
import feature.bangboo.model.stubBangbooList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBangbooRepository : BangbooRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getBangbooList(): Flow<List<BangbooListItem>> = flow {
        emit(stubBangbooList)
    }

    override suspend fun requestAndUpdateBangbooListDB(): Result<Unit> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(Unit)
        }
    }

    override suspend fun getBangbooDetail(id: Int): Result<BangbooDetail> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubBangbooDetail)
        }
    }
}