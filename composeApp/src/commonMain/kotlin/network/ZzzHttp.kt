/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.agent.model.AgentDetailResponse
import feature.agent.model.AgentsListResponse
import feature.bangboo.model.BangbooDetailResponse
import feature.bangboo.model.BangbooListResponse
import feature.drive.model.DrivesListResponse
import feature.home.model.response.ImageBannerResponse
import feature.wengine.model.WEngineDetailResponse
import feature.wengine.model.WEnginesListResponse
import root.model.BannerResponse

interface ZzzHttp {
    val defaultTimeout: Long
    val longTimeout: Long
    val languagePath: String
    suspend fun requestBanner(): BannerResponse
    suspend fun requestImageBanner(): ImageBannerResponse
    suspend fun requestAgentsList(): AgentsListResponse
    suspend fun requestAgentDetail(id: Int): AgentDetailResponse
    suspend fun requestWEnginesList(): WEnginesListResponse
    suspend fun requestWEngineDetail(id: Int): WEngineDetailResponse
    suspend fun requestBangbooList(): BangbooListResponse
    suspend fun requestBangbooDetail(id: Int): BangbooDetailResponse
    suspend fun requestDrivesList(): DrivesListResponse
}