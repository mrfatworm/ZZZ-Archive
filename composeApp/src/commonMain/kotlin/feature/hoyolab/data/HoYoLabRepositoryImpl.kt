/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data

import feature.hoyolab.data.mapper.toPlayerAccountInfo
import feature.hoyolab.model.PlayerAccountInfo
import feature.hoyolab.model.PlayerDetailResponse
import kotlinx.coroutines.withTimeout
import network.HoYoLabHttp

class HoYoLabRepositoryImpl(private val httpClient: HoYoLabHttp) : HoYoLabRepository {
    override suspend fun requestUserGameRolesByLToken(
        region: String, lToken: String, ltUid: String
    ): Result<List<PlayerAccountInfo>> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestUserGameRolesByLToken(region, lToken, ltUid)
            }
            Result.success(result.data.list.map { it.toPlayerAccountInfo() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun requestPlayerDetail(
        uid: Int, region: String, lToken: String, ltUid: String
    ): Result<PlayerDetailResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestPlayerDetail(uid, region, lToken, ltUid)
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}