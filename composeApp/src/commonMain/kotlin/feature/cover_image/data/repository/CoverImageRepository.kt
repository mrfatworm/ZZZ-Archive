/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.data.repository

import feature.cover_image.data.database.CoverImageListItemEntity
import kotlinx.coroutines.flow.Flow

interface CoverImageRepository {
    suspend fun getCoverImagesList(): Flow<List<CoverImageListItemEntity>>
    suspend fun requestAndUpdateCoverImagesListDB(): Result<Unit>
}