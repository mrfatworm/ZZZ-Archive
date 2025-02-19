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
import kotlinx.coroutines.flow.map
import network.ZzzHttp

class DriveRepositoryImpl(
    private val httpClient: ZzzHttp,
    private val drivesListDB: DrivesListDao
) : DriveRepository {
    override suspend fun getDrivesList(languagePath: String): Flow<List<DrivesListItemEntity>> {
        val cachedDrivesList = drivesListDB.getDrivesList()
        if (cachedDrivesList.first().isEmpty()) {
            requestAndUpdateDrivesListDB(languagePath)
        }
        return drivesListDB.getDrivesList().map { it.reversed() }
    }

    override suspend fun requestAndUpdateDrivesListDB(languagePath: String): Result<Unit> {
        return try {
            val result = httpClient.requestDrivesList(languagePath)
            drivesListDB.setDrivesList(result.drives.map { it.toDriveListEntity() })
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}