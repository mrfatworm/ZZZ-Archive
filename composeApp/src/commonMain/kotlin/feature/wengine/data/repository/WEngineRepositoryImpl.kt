/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.data.repository

import feature.wengine.data.database.WEnginesListDao
import feature.wengine.data.mapper.toWEngineDetail
import feature.wengine.data.mapper.toWEnginesListItem
import feature.wengine.data.mapper.toWEnginesListItemEntity
import feature.wengine.model.WEngineDetail
import feature.wengine.model.WEnginesListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withTimeout
import network.ZzzHttp

class WEngineRepositoryImpl(
    private val httpClient: ZzzHttp, private val wEnginesListDao: WEnginesListDao
) : WEngineRepository {
    override suspend fun getWEnginesList(): Flow<List<WEnginesListItem>> {
        val cachedWEnginesList = wEnginesListDao.getWEnginesList()
        if (cachedWEnginesList.first().isEmpty()) {
            requestAndUpdateWEnginesListDB()
        }
        return wEnginesListDao.getWEnginesList().map { wEnginesList ->
            wEnginesList.map { it.toWEnginesListItem() }.reversed()
        }
    }

    override suspend fun requestAndUpdateWEnginesListDB(): Result<Unit> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestWEnginesList()
            }
            wEnginesListDao.setWEnginesList(result.wEngines.map { it.toWEnginesListItemEntity() })
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getWEngineDetail(id: Int): Result<WEngineDetail> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestWEngineDetail(id)
            }
            Result.success(result.toWEngineDetail())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}