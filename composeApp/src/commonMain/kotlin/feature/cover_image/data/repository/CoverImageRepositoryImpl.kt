/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.data.repository

import feature.cover_image.data.database.CoverImageListItemEntity
import feature.cover_image.data.database.CoverImagesListDao
import feature.cover_image.data.mapper.toCoverImageListItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import network.ZzzHttp

class CoverImageRepositoryImpl(
    private val httpClient: ZzzHttp,
    private val database: CoverImagesListDao
) : CoverImageRepository {
    override suspend fun getCoverImagesList(): Flow<List<CoverImageListItemEntity>> {
        val cachedCoverImageList = database.getCoverImagesList()
        if (cachedCoverImageList.first().isEmpty()) {
            requestAndUpdateCoverImagesListDB()
        }
        return database.getCoverImagesList()
    }

    override suspend fun requestAndUpdateCoverImagesListDB(): Result<Unit> {
        return try {
            val result = httpClient.requestCoverImage()
            database.setCoverImagesList(result.coverImages.map { it.toCoverImageListItemEntity() })
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}