/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.data.respository

import feature.drive.data.database.DrivesListItemEntity
import kotlinx.coroutines.flow.Flow

interface DriveRepository {
    suspend fun getDrivesList(): Flow<List<DrivesListItemEntity>>
    suspend fun requestAndUpdateDrivesListDB(): Result<Unit>
}