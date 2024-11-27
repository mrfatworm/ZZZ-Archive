/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.data.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCoverImagesListDao : CoverImagesListDao {
    private val coverImagesList = mutableListOf(stubCoverImageListItemEntity)

    override suspend fun setCoverImagesList(coverImagesList: List<CoverImageListItemEntity>) {
        this.coverImagesList.clear()
        this.coverImagesList.addAll(coverImagesList)
    }

    override fun getCoverImagesList(): Flow<List<CoverImageListItemEntity>> = flow {
        emit(coverImagesList)
    }

    override suspend fun deleteCoverImagesList() {
        coverImagesList.clear()
    }
}