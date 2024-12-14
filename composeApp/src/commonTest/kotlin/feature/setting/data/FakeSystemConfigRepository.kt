/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSystemConfigRepository : SystemConfigRepository {
    private var bannerIgnoreId = 1
    private var coverImageDBVersion = 1
    private var agentListDBVersion = 1
    private var wEngineListDBVersion = 1
    private var bangbooListDBVersion = 1
    private var driveListDBVersion = 1


    override fun getBannerIgnoreId(): Flow<Int> = flow {
        emit(bannerIgnoreId)
    }

    override suspend fun setBannerIgnoreId(value: Int) {
        bannerIgnoreId = value
    }

    override fun getCoverImageDBVersion(): Flow<Int> = flow {
        emit(coverImageDBVersion)
    }

    override suspend fun setCoverImageDBVersion(value: Int) {
        coverImageDBVersion = value
    }

    override fun getAgentListDBVersion(): Flow<Int> = flow {
        emit(agentListDBVersion)
    }

    override suspend fun setAgentListDBVersion(value: Int) {
        agentListDBVersion = value
    }

    override fun getWEngineListDBVersion(): Flow<Int> = flow {
        emit(wEngineListDBVersion)
    }

    override suspend fun setWEngineListDBVersion(value: Int) {
        wEngineListDBVersion = value
    }

    override fun getBangbooListDBVersion(): Flow<Int> = flow {
        emit(bangbooListDBVersion)
    }

    override suspend fun setBangbooListDBVersion(value: Int) {
        bangbooListDBVersion = value
    }

    override fun getDriveListDBVersion(): Flow<Int> = flow {
        emit(driveListDBVersion)
    }

    override suspend fun setDriveListDBVersion(value: Int) {
        driveListDBVersion = value
    }

    override suspend fun clear() {
        bannerIgnoreId = 0
        coverImageDBVersion = 0
        agentListDBVersion = 0
        wEngineListDBVersion = 0
        bangbooListDBVersion = 0
        driveListDBVersion = 0
    }
}