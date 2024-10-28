/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package network

import app.agent.model.AgentDetailResponse
import app.agent.model.AgentsListResponse
import app.agent.model.stubAgentDetailResponse
import app.agent.model.stubAgentsListResponse
import app.bangboo.model.BangbooListResponse
import app.bangboo.model.stubBangbooListResponse
import app.drive.model.DriveListResponse
import app.drive.model.stubDriveListResponse
import app.home.model.ImageBannerResponse
import app.home.model.stubImageBannerResponse
import app.wengine.model.WEngineDetailResponse
import app.wengine.model.WEnginesListResponse
import app.wengine.model.stubWEngineDetailResponse
import app.wengine.model.stubWEnginesListResponse
import mainfunc.model.BannerResponse
import mainfunc.model.stubBannerResponse


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

    override suspend fun requestImageBanner(): ImageBannerResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubImageBannerResponse
        }
    }

    override suspend fun requestAgentList(): AgentsListResponse {
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

    override suspend fun requestWEngineList(): WEnginesListResponse {
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

    override suspend fun requestDriveList(): DriveListResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubDriveListResponse
        }
    }
}