/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package network

import app.agent.model.AgentsListResponse
import app.agent.model.stubAgentsListResponse
import app.bangboo.model.BangbooListResponse
import app.bangboo.model.stubBangbooListResponse
import app.drive.model.DriveListResponse
import app.drive.model.stubDriveListResponse
import app.home.model.BannerResponse
import app.home.model.stubBannerResponse
import app.wengine.model.WEnginesListResponse
import app.wengine.model.stubWEnginesListResponse


class FakeZzzHttp : ZzzHttp {
    override val defaultTimeout = 5000L
    override val longTimeout = 10000L
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

    override suspend fun requestAgentList(): AgentsListResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubAgentsListResponse
        }
    }

    override suspend fun requestWEngineList(): WEnginesListResponse {
        return if (isError) {
            throw Exception()
        } else {
            stubWEnginesListResponse
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