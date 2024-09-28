/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package network

import app.agent.model.AgentsListResponse
import app.agent.model.stubAgentsListResponse
import app.home.model.BannerResponse
import app.home.model.stubBannerResponse


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
}