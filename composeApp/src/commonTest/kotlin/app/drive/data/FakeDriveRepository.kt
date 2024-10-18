/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.drive.data

import app.drive.model.DriveListResponse
import app.drive.model.stubDriveListResponse
import utils.ZzzResult

class FakeDriveRepository : DriveRepository {
    private var isError = false

    fun setError(isError: Boolean) {
        this.isError = isError
    }

    override suspend fun getDrivesList(): ZzzResult<DriveListResponse> {
        return if (isError) {
            ZzzResult.Error(Exception())
        } else {
            ZzzResult.Success(stubDriveListResponse)
        }
    }
}