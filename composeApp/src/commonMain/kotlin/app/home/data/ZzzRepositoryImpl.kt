/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.data

import database.ZzzDatabase
import app.home.model.OfficialActivities
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