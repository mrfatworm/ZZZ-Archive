/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.data.respository

import feature.drive.data.database.DrivesListDao
import feature.drive.data.database.DrivesListItemEntity
import feature.drive.data.mapper.toDriveListEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withTimeout
import network.ZzzHttp

class DriveRepositoryImpl(
    private val httpClient: ZzzHttp,
    private val drivesListDB: DrivesListDao
) : DriveRepository {
    override suspend fun getDrivesList(): Flow<List<DrivesListItemEntity>> {
        val cachedDrivesList = drivesListDB.getDrivesList()
        if (cachedDrivesList.first().isEmpty()) {
            requestAndUpdateDrivesListDB()
        }
        return drivesListDB.getDrivesList()
    }

    override suspend fun requestAndUpdateDrivesListDB(): Result<Unit> {
        return try {
            val result = withTimeout(httpClient.defaultTimeout) {
                httpClient.requestDrivesList()
            }
            drivesListDB.setDrivesList(result.drives.map { it.toDriveListEntity() })
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}