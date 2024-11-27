/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.data

import feature.home.model.AssetVersionResponse
import feature.home.model.stubAssetVersionResponse

class FakeAssetVersionRepository : AssetVersionRepository {
    private var error = false

    fun setError(value: Boolean) {
        error = value
    }

    override suspend fun requestAssetVersion(): Result<AssetVersionResponse> {
        return if (error) {
            Result.failure(Exception("Fake error"))
        } else {
            Result.success(stubAssetVersionResponse)
        }
    }
}