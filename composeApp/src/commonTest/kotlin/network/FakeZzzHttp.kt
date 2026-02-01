/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.agent.model.AgentsListResponse
import feature.agent.model.stubAgentsListResponse
import feature.banner.data.BannerResponse
import feature.banner.data.stubBannerResponse
import feature.cover.model.CoverImageListResponse
import feature.cover.model.stubCoverImageResponse
import feature.home.model.AssetVersionResponse
import feature.home.model.stubAssetVersionResponse

class FakeZzzHttp : ZzzHttp {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun requestAssetVersion(): AssetVersionResponse = if (isError) {
        throw Exception()
    } else {
        stubAssetVersionResponse
    }

    override suspend fun requestBanner(languagePath: String): BannerResponse = if (isError) {
        throw Exception()
    } else {
        stubBannerResponse
    }

    override suspend fun requestCoverImage(): CoverImageListResponse = if (isError) {
        throw Exception()
    } else {
        stubCoverImageResponse
    }

    override suspend fun requestAgentsList(): AgentsListResponse = if (isError) {
        throw Exception()
    } else {
        stubAgentsListResponse
    }
}
