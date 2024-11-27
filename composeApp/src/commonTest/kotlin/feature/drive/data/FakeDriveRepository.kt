/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.data

import feature.drive.data.database.DrivesListItemEntity
import feature.drive.data.respository.DriveRepository
import feature.drive.model.stubDrivesListResponse
import kotlinx.coroutines.flow.Flow

class FakeDriveRepository : DriveRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getDrivesList(): Flow<List<DrivesListItemEntity>> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubDrivesListResponse)
        }
    }
}