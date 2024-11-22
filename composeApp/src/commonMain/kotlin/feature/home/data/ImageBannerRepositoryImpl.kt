/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.data

import feature.home.model.response.ImageBannerResponse
import kotlinx.coroutines.withTimeout
import network.ZzzHttp
import utils.ZzzResult

class ImageBannerRepositoryImpl(private val httpClient: ZzzHttp) : ImageBannerRepository {
    override suspend fun getImageBanner(): ZzzResult<ImageBannerResponse> {
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