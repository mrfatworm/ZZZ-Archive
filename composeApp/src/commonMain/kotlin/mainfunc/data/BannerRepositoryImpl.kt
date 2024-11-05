/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package mainfunc.data

import kotlinx.coroutines.withTimeout
import mainfunc.model.BannerResponse
import network.ZzzHttp
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