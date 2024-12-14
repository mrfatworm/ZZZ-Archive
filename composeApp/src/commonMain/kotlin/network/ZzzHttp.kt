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
    suspend fun requestAssetVersion(): AssetVersionResponse
    suspend fun requestCoverImage(): CoverImageListResponse
    suspend fun requestBanner(languagePath: String): BannerResponse
    suspend fun requestAgentsList(languagePath: String): AgentsListResponse
    suspend fun requestAgentDetail(id: Int, languagePath: String): AgentDetailResponse
    suspend fun requestWEnginesList(languagePath: String): WEnginesListResponse
    suspend fun requestWEngineDetail(id: Int, languagePath: String): WEngineDetailResponse
    suspend fun requestBangbooList(languagePath: String): BangbooListResponse
    suspend fun requestBangbooDetail(id: Int, languagePath: String): BangbooDetailResponse
    suspend fun requestDrivesList(languagePath: String): DrivesListResponse
}