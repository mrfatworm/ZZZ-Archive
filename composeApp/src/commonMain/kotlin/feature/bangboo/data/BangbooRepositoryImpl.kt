/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.data

import feature.bangboo.model.BangbooDetailResponse
import feature.bangboo.model.BangbooListResponse
import kotlinx.coroutines.withTimeout
import network.ZzzHttp

class BangbooRepositoryImpl(private val httpClient: ZzzHttp) : BangbooRepository {
    override suspend fun getBangbooList(): Result<BangbooListResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestBangbooList()
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBangbooDetail(id: Int): Result<BangbooDetailResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestBangbooDetail(id)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}