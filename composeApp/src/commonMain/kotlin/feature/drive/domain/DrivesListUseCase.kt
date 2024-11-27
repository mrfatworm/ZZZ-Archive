/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.domain

import feature.drive.data.respository.DriveRepository

class DrivesListUseCase(private val driveRepository: DriveRepository) {
    suspend fun invoke() = driveRepository.getDrivesList()
    suspend fun requestAndUpdateDrivesListDB() = driveRepository.requestAndUpdateDrivesListDB()
}