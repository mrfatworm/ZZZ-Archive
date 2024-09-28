/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package network

import app.agent.model.AgentsListResponse
import app.home.model.BannerResponse

interface ZzzHttp {
    val defaultTimeout: Long
    val longTimeout: Long
    suspend fun requestBanner(): BannerResponse
    suspend fun requestAgentList(): AgentsListResponse
}