/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.banner.data

import kotlinx.coroutines.withTimeout
import network.ZzzHttp

class BannerRepositoryImpl(private val httpClient: ZzzHttp) : BannerRepository {
    override suspend fun getBanner(): Result<BannerResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestBanner()
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}