/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package root.data

import kotlinx.coroutines.withTimeout
import network.ZzzHttp
import root.model.BannerResponse
import utils.ZzzResult

class BannerRepositoryImpl(private val httpClient: ZzzHttp) : BannerRepository {
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