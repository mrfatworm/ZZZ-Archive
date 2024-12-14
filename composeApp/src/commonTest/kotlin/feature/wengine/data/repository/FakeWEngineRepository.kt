/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.data.repository

import feature.wengine.model.WEngineDetail
import feature.wengine.model.WEnginesListItem
import feature.wengine.model.stubWEngineDetail
import feature.wengine.model.stubWEnginesList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeWEngineRepository : WEngineRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getWEnginesList(languagePath: String): Flow<List<WEnginesListItem>> =
        flow {
        emit(stubWEnginesList)
    }

    override suspend fun requestAndUpdateWEnginesListDB(languagePath: String): Result<Unit> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(Unit)
        }
    }

    override suspend fun getWEngineDetail(id: Int, languagePath: String): Result<WEngineDetail> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubWEngineDetail)
        }
    }
}