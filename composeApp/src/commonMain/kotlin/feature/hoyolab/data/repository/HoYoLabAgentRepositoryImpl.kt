/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.repository

import feature.hoyolab.data.mapper.toMyAgentListItem
import feature.hoyolab.model.MyAgentListItem
import network.HoYoLabHttp

class HoYoLabAgentRepositoryImpl(private val httpClient: HoYoLabHttp) : HoYoLabAgentRepository {

    override suspend fun requestPlayerAgentList(
        languageCode: String,
        uid: Int,
        region: String,
        lToken: String,
        ltUid: String
    ): Result<List<MyAgentListItem>> {
        return try {
            val result = httpClient.requestMyAgentList(languageCode, uid, region, lToken, ltUid)
            Result.success(result.data.avatarList.map { it.toMyAgentListItem() })
        } catch (e: Exception) {
            println("Fail: $e")
            Result.failure(e)
        }
    }

}