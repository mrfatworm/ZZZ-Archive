/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover.data

import feature.cover.data.database.CoverImageListItemEntity
import feature.cover.data.database.stubCoverImageListItemEntity
import feature.cover.data.repository.CoverImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCoverImageRepository : CoverImageRepository {
    private var isError = false

    var requestAndUpdateCoverImagesListDBCount = 0
        private set

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getCoverImagesList(): Flow<List<CoverImageListItemEntity>> = flow {
        emit(listOf(stubCoverImageListItemEntity))
    }

    override suspend fun requestAndUpdateCoverImagesListDB(): Result<Unit> {
        requestAndUpdateCoverImagesListDBCount++
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(Unit)
        }
    }
}
