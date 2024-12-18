/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.banner.data

import network.ZzzHttp

class BannerRepositoryImpl(private val httpClient: ZzzHttp) : BannerRepository {
    override suspend fun getBanner(languageCode: String): Result<BannerResponse> {
        return try {
            val result = httpClient.requestBanner(languageCode)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}