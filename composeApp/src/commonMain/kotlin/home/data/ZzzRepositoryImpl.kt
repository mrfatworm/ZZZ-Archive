/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: Apache-2.0
 */

package home.data

import database.ZzzDatabase
import home.model.OfficialActivities
import kotlinx.coroutines.withTimeout
import network.ZzzHttpClient
import utils.ZzzResult

class ZzzRepositoryImpl(private val database: ZzzDatabase, private val httpClient: ZzzHttpClient) :
    ZzzRepository {
    override suspend fun getActivities(): ZzzResult<OfficialActivities> {
            return try {
                val result = withTimeout(5000) {
                    httpClient.requestActivities()
                }
                ZzzResult.Success(result)
            } catch (e: Exception) {
                ZzzResult.Error(e)
            }
        }
}