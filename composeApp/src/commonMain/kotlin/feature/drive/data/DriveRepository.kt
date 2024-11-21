/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.data

import feature.drive.model.DrivesListResponse
import utils.ZzzResult

interface DriveRepository {
    suspend fun getDrivesList(): ZzzResult<DrivesListResponse>
}