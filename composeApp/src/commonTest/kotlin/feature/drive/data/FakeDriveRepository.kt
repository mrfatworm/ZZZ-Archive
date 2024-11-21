/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.data

import feature.drive.model.DrivesListResponse
import feature.drive.model.stubDrivesListResponse
import utils.ZzzResult

class FakeDriveRepository : DriveRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getDrivesList(): ZzzResult<DrivesListResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubDrivesListResponse)
        }
    }
}