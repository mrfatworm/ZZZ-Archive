/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.data

import feature.drive.data.database.DrivesListItemEntity
import feature.drive.data.database.stubDrivesListItemEntity
import feature.drive.data.respository.DriveRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDriveRepository : DriveRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getDrivesList(languagePath: String): Flow<List<DrivesListItemEntity>> =
        flow {
        emit(listOf(stubDrivesListItemEntity))
    }

    override suspend fun requestAndUpdateDrivesListDB(languagePath: String): Result<Unit> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(Unit)
        }
    }
}