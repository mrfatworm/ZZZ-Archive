/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.cover_image.data

import kotlinx.coroutines.withTimeout
import network.ZzzHttp

class CoverImageRepositoryImpl(private val httpClient: ZzzHttp) : CoverImageRepository {
    override suspend fun getImageBanner(): Result<CoverImageResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestImageBanner()
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}