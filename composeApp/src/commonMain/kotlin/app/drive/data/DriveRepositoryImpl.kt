/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.drive.data

import app.drive.model.DriveListResponse
import kotlinx.coroutines.withTimeout
import network.ZzzHttp
import utils.ZzzResult

class DriveRepositoryImpl(private val httpClient: ZzzHttp) : DriveRepository {
    override suspend fun getDrivesList(): ZzzResult<DriveListResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestDriveList()
            }
            ZzzResult.Success(result)
        } catch (e: Exception) {
            ZzzResult.Error(e)
        }
    }
}