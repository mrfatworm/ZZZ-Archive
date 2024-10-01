/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine.data

import app.wengine.model.WEnginesListResponse
import kotlinx.coroutines.withTimeout
import network.ZzzHttp
import utils.ZzzResult

class WEngineRepositoryImpl(private val httpClient: ZzzHttp) : WEngineRepository {
    override suspend fun getWEnginesList(): ZzzResult<WEnginesListResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestWEngineList()
            }
            ZzzResult.Success(result)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }
}