/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package network

import feature.agent.model.AgentDetailResponse
import feature.agent.model.AgentsListResponse
import feature.bangboo.model.BangbooDetailResponse
import feature.bangboo.model.BangbooListResponse
import feature.banner.data.BannerResponse
import feature.cover_image.model.CoverImageListResponse
import feature.drive.model.DrivesListResponse
import feature.home.model.AssetVersionResponse
import feature.wengine.model.WEngineDetailResponse
import feature.wengine.model.WEnginesListResponse

interface ZzzHttp {
    val defaultTimeout: Long
    val languagePath: String
    suspend fun requestAssetVersion(): AssetVersionResponse
    suspend fun requestBanner(): BannerResponse
    suspend fun requestCoverImage(): CoverImageListResponse
    suspend fun requestAgentsList(): AgentsListResponse
    suspend fun requestAgentDetail(id: Int): AgentDetailResponse
    suspend fun requestWEnginesList(): WEnginesListResponse
    suspend fun requestWEngineDetail(id: Int): WEngineDetailResponse
    suspend fun requestBangbooList(): BangbooListResponse
    suspend fun requestBangbooDetail(id: Int): BangbooDetailResponse
    suspend fun requestDrivesList(): DrivesListResponse
}