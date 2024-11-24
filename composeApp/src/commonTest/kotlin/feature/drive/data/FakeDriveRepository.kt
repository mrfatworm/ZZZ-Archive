/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.data

import feature.drive.model.DrivesListResponse
import feature.drive.model.stubDrivesListResponse

class FakeDriveRepository : DriveRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getDrivesList(): Result<DrivesListResponse> {
        return if (isError) {
            Result.failure(Exception())
        } else {
            Result.success(stubDrivesListResponse)
        }
    }
}