/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.data.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDrivesListDao : DrivesListDao {
    private val drivesList = mutableListOf(stubDrivesListItemEntity)

    override suspend fun setDrivesList(agentsList: List<DrivesListItemEntity>) {
        drivesList.clear()
        drivesList.addAll(agentsList)
    }

    override fun getDrivesList(): Flow<List<DrivesListItemEntity>> = flow {
        emit(drivesList)
    }

    override suspend fun deleteDrivesList() {
        drivesList.clear()
    }
}