/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.agent.model.AgentDetailResponse
import feature.agent.model.AgentsListResponse
import feature.agent.model.stubAgentDetailResponse
import feature.agent.model.stubAgentsListResponse
import feature.bangboo.model.BangbooDetailResponse
import feature.bangboo.model.BangbooListResponse
import feature.bangboo.model.stubBangbooDetailResponse
import feature.bangboo.model.stubBangbooListResponse
import feature.banner.data.BannerResponse
import feature.banner.data.stubBannerResponse
import feature.cover_image.data.CoverImageResponse
import feature.cover_image.data.stubCoverImageResponse
import feature.drive.model.DrivesListResponse
import feature.drive.model.stubDrivesListResponse
import feature.wengine.model.WEngineDetailResponse
import feature.wengine.model.WEnginesListResponse
import feature.wengine.model.stubWEngineDetailResponse
import feature.wengine.model.stubWEnginesListResponse


class FakeZzzHttp : ZzzHttp {
    override val defaultTimeout = 5000L
    override val longTimeout = 10000L
    override val languagePath = "zh"

    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun requestBanner(): BannerResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubBannerResponse
        }
    }

    override suspend fun requestImageBanner(): CoverImageResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubCoverImageResponse
        }
    }

    override suspend fun requestAgentsList(): AgentsListResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubAgentsListResponse
        }
    }

    override suspend fun requestAgentDetail(id: Int): AgentDetailResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubAgentDetailResponse
        }
    }

    override suspend fun requestWEnginesList(): WEnginesListResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubWEnginesListResponse
        }
    }

    override suspend fun requestWEngineDetail(id: Int): WEngineDetailResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubWEngineDetailResponse
        }
    }

    override suspend fun requestBangbooList(): BangbooListResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubBangbooListResponse
        }
    }

    override suspend fun requestBangbooDetail(id: Int): BangbooDetailResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubBangbooDetailResponse
        }
    }

    override suspend fun requestDrivesList(): DrivesListResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubDrivesListResponse
        }
    }
}