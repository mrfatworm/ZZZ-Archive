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
import kotlinx.coroutines.withTimeout
import network.ZzzHttp

class CoverImageRepositoryImpl(
    private val httpClient: ZzzHttp,
    private val database: CoverImagesListDao
) : CoverImageRepository {
    override suspend fun getCoverImagesList(): Flow<List<CoverImageListItemEntity>> {
        println("Lance: getCoverImages Flow")
        val cachedCoverImageList = database.getCoverImagesList()
        if (cachedCoverImageList.first().isEmpty()) {
            requestAndUpdateCoverImagesListDB()
        }
        return database.getCoverImagesList()
    }

    override suspend fun requestAndUpdateCoverImagesListDB(): Result<Unit> {
        println("Lance: getCoverImages Update")
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestCoverImage()
            }
            database.setCoverImagesList(result.coverImages.map { it.toCoverImageListItemEntity() })
            println("Lance: getCoverImages Update Success")
            Result.success(Unit)
        } catch (e: Exception) {
            println("Lance: getCoverImages Update fail: $e")
            Result.failure(e)
        }
    }
}