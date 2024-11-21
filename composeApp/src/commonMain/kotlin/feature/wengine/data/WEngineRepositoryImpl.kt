/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.data

import feature.wengine.model.WEngineDetailResponse
import feature.wengine.model.WEnginesListResponse
import kotlinx.coroutines.withTimeout
import network.ZzzHttp
import utils.ZzzResult

class WEngineRepositoryImpl(private val httpClient: ZzzHttp) : WEngineRepository {
    override suspend fun getWEnginesList(): ZzzResult<WEnginesListResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestWEnginesList()
            }
            ZzzResult.Success(result)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }

    override suspend fun getWEngineDetail(id: Int): ZzzResult<WEngineDetailResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestWEngineDetail(id)
            }
            ZzzResult.Success(result)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }
}