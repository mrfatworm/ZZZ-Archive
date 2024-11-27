/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.home.data

import feature.home.model.AssetVersionResponse

interface AssetVersionRepository {
    suspend fun requestAssetVersion(): Result<AssetVersionResponse>
}