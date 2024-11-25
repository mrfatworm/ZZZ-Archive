/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.data

import feature.drive.model.DrivesListResponse
import kotlinx.coroutines.withTimeout
import network.ZzzHttp

class DriveRepositoryImpl(private val httpClient: ZzzHttp) : DriveRepository {
    override suspend fun getDrivesList(): Result<DrivesListResponse> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestDrivesList()
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}