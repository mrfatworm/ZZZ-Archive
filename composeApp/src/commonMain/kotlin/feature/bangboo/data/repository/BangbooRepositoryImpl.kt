/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.data.repository

import feature.bangboo.data.database.BangbooListDao
import feature.bangboo.data.mapper.toBangbooDetail
import feature.bangboo.data.mapper.toBangbooListItem
import feature.bangboo.data.mapper.toBangbooListItemEntity
import feature.bangboo.model.BangbooDetail
import feature.bangboo.model.BangbooListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withTimeout
import network.ZzzHttp

class BangbooRepositoryImpl(
    private val httpClient: ZzzHttp,
    private val bangbooListDB: BangbooListDao
) : BangbooRepository {
    override suspend fun getBangbooList(): Flow<List<BangbooListItem>> {
        val cachedBangbooList = bangbooListDB.getBangbooList()
        if (cachedBangbooList.first().isEmpty()) {
            requestAndUpdateBangbooListDB()
        }
        return bangbooListDB.getBangbooList().map { localBangbooList ->
            localBangbooList.map { it.toBangbooListItem() }.reversed()
        }
    }

    override suspend fun requestAndUpdateBangbooListDB(): Result<Unit> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestBangbooList()
            }
            bangbooListDB.setBangbooList(result.bangboo.map { it.toBangbooListItemEntity() })
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBangbooDetail(id: Int): Result<BangbooDetail> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestBangbooDetail(id)
            }
            Result.success(result.toBangbooDetail())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}