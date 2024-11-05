/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.drive.data

import app.drive.model.DrivesListResponse
import utils.ZzzResult

interface DriveRepository {
    suspend fun getDrivesList(): ZzzResult<DrivesListResponse>
}