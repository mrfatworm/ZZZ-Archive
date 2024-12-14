/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.domain

import feature.drive.data.FakeDriveRepository
import feature.drive.data.database.stubDrivesListItemEntity
import feature.setting.domain.FakeLanguageUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DrivesListUseCaseTest {
    private val driveRepository = FakeDriveRepository()
    private val languageUseCase = FakeLanguageUseCase()
    private val drivesListUseCase = DrivesListUseCase(driveRepository, languageUseCase)

    @Test
    fun `Get drives list`() = runTest {
        val result = drivesListUseCase.invoke().first()
        assertEquals(result.first(), stubDrivesListItemEntity)
    }

    @Test
    fun `Request drives list success`() = runTest {
        val result = drivesListUseCase.updateDrivesList().getOrNull()
        assertEquals(result, Unit)
    }

    @Test
    fun `Request drives list error`() = runTest {
        driveRepository.setError(true)
        val result = drivesListUseCase.updateDrivesList().getOrNull()
        assertNull(result)
    }
}