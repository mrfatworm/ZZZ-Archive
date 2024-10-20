/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package network

import app.agent.model.AgentDetailResponse
import app.agent.model.AgentsListResponse
import app.bangboo.model.BangbooListResponse
import app.drive.model.DriveListResponse
import app.home.model.ImageBannerResponse
import app.wengine.model.WEnginesListResponse
import mainfunc.model.BannerResponse

interface ZzzHttp {
    val defaultTimeout: Long
    val longTimeout: Long
    val languagePath: String
    suspend fun requestBanner(): BannerResponse
    suspend fun requestImageBanner(): ImageBannerResponse
    suspend fun requestAgentList(): AgentsListResponse
    suspend fun requestAgentDetail(id: Int): AgentDetailResponse
    suspend fun requestWEngineList(): WEnginesListResponse
    suspend fun requestBangbooList(): BangbooListResponse
    suspend fun requestDriveList(): DriveListResponse
}