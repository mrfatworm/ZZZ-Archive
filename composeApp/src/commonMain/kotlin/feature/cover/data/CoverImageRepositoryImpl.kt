/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.cover.data

import kotlinx.coroutines.withTimeout
import network.ZzzHttp
import utils.ZzzResult

class CoverImageRepositoryImpl(private val httpClient: ZzzHttp) : CoverImageRepository {
    override suspend fun getImageBanner(): ZzzResult<CoverImageResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestImageBanner()
            }
            ZzzResult.Success(result)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }
}