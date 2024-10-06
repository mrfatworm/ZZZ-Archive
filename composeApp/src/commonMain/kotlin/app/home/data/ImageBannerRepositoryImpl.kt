/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.ImageBannerResponse
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