/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import app.home.model.BannerResponse
import kotlinx.coroutines.withTimeout
import network.ZzzHttpClient
import utils.ZzzResult

class BannerRepositoryImpl(private val httpClient: ZzzHttpClient) : BannerRepository {
    override suspend fun getBanner(): ZzzResult<BannerResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestBanner()
            }
            ZzzResult.Success(result)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }
}