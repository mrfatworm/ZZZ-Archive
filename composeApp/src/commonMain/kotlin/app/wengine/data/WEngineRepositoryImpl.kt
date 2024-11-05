/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.wengine.data

import app.wengine.model.WEngineDetailResponse
import app.wengine.model.WEnginesListResponse
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