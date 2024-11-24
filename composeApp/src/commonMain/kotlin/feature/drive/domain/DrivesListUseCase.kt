/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.domain

import feature.drive.data.DriveRepository

class DrivesListUseCase(private val driveRepository: DriveRepository) {
    suspend fun invoke() = driveRepository.getDrivesList().map { it.drives }
}