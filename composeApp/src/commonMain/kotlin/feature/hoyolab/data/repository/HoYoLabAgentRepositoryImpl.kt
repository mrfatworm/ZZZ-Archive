/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.repository

import feature.hoyolab.data.mapper.toMyAgentDetailListItem
import feature.hoyolab.data.mapper.toMyAgentListItem
import feature.hoyolab.model.MyAgentListItem
import feature.hoyolab.model.my_agent_detail.MyAgentDetailListItem
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
            Result.failure(e)
        }
    }

    override suspend fun requestPlayerAgentDetail(
        languageCode: String,
        uid: Int,
        region: String,
        lToken: String,
        ltUid: String,
        agentId: Int
    ): Result<MyAgentDetailListItem> {
        return try {
            val result =
                httpClient.requestMyAgentDetail(languageCode, uid, region, agentId, lToken, ltUid)
            Result.success(result.data.avatarList[0].toMyAgentDetailListItem())
        } catch (e: Exception) {
            print("Error: $e")
            Result.failure(e)
        }
    }

}