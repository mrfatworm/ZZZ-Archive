/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.data

import feature.home.model.AssetVersionResponse
import network.ZzzHttp

class AssetVersionRepositoryImpl(private val httpClient: ZzzHttp) : AssetVersionRepository {
    override suspend fun requestAssetVersion(): Result<AssetVersionResponse> {
        return try {
            val result = httpClient.requestAssetVersion()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}