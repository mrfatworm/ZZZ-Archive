/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.data

import feature.wengine.model.WEngineDetailResponse
import feature.wengine.model.WEnginesListResponse
import kotlinx.coroutines.withTimeout
import network.ZzzHttp

class WEngineRepositoryImpl(private val httpClient: ZzzHttp) : WEngineRepository {
    override suspend fun getWEnginesList(): Result<WEnginesListResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestWEnginesList()
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getWEngineDetail(id: Int): Result<WEngineDetailResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestWEngineDetail(id)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}