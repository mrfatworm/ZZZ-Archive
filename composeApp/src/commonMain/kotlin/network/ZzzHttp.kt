/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.agent.model.AgentsListResponse
import feature.banner.data.BannerResponse
import feature.cover.model.CoverImageListResponse
import feature.home.model.AssetVersionResponse

interface ZzzHttp {
    suspend fun requestAssetVersion(): AssetVersionResponse

    suspend fun requestCoverImage(): CoverImageListResponse

    suspend fun requestBanner(languagePath: String): BannerResponse

    suspend fun requestAgentsList(): AgentsListResponse
}
