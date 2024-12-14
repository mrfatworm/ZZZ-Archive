/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.domain

import feature.drive.data.respository.DriveRepository
import feature.setting.domain.LanguageUseCase
import kotlinx.coroutines.flow.first

class DrivesListUseCase(
    private val driveRepository: DriveRepository, private val languageUseCase: LanguageUseCase
) {
    suspend fun invoke() =
        driveRepository.getDrivesList(languageUseCase.getLanguage().first().officialCode)

    suspend fun updateDrivesList() = driveRepository.requestAndUpdateDrivesListDB(
        languageUseCase.getLanguage().first().officialCode
    )
}