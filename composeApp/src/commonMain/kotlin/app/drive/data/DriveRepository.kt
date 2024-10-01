/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.drive.data

import app.drive.model.DriveListResponse
import utils.ZzzResult

interface DriveRepository {
    suspend fun getDrivesList(): ZzzResult<DriveListResponse>
}