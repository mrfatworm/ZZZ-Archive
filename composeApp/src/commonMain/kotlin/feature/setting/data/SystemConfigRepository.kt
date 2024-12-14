/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.data

import kotlinx.coroutines.flow.Flow

interface SystemConfigRepository {
    fun getBannerIgnoreId(): Flow<Int>
    suspend fun setBannerIgnoreId(value: Int)
    fun getCoverImageDBVersion(): Flow<Int>
    suspend fun setCoverImageDBVersion(value: Int)
    fun getAgentListDBVersion(): Flow<Int>
    suspend fun setAgentListDBVersion(value: Int)
    fun getWEngineListDBVersion(): Flow<Int>
    suspend fun setWEngineListDBVersion(value: Int)
    fun getBangbooListDBVersion(): Flow<Int>
    suspend fun setBangbooListDBVersion(value: Int)
    fun getDriveListDBVersion(): Flow<Int>
    suspend fun setDriveListDBVersion(value: Int)
    suspend fun clear()
}