/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package network

import app.agent.model.AgentsListResponse
import app.bangboo.model.BangbooListResponse
import app.drive.model.DriveListResponse
import app.home.model.BannerResponse
import app.wengine.model.WEnginesListResponse

interface ZzzHttp {
    val defaultTimeout: Long
    val longTimeout: Long
    suspend fun requestBanner(): BannerResponse
    suspend fun requestAgentList(): AgentsListResponse
    suspend fun requestWEngineList(): WEnginesListResponse
    suspend fun requestBangbooList(): BangbooListResponse
    suspend fun requestDriveList(): DriveListResponse
}