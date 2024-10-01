/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.bangboo.data

import app.bangboo.model.BangbooListResponse
import kotlinx.coroutines.withTimeout
import network.ZzzHttp
import utils.ZzzResult

class BangbooRepositoryImpl(private val httpClient: ZzzHttp) : BangbooRepository {
    override suspend fun getBangbooList(): ZzzResult<BangbooListResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestBangbooList()
            }
            ZzzResult.Success(result)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }
}