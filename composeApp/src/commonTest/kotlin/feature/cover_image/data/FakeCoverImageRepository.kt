/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.data

import feature.cover_image.data.database.CoverImageListItemEntity
import feature.cover_image.data.repository.CoverImageRepository
import feature.cover_image.model.stubCoverImageResponse
import kotlinx.coroutines.flow.Flow

class FakeCoverImageRepository : CoverImageRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getCoverImagesList(): Flow<List<CoverImageListItemEntity>> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubCoverImageResponse)
        }
    }
}