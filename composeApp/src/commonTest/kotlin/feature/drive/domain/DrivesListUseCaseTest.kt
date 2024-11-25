/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.domain

import feature.drive.data.FakeDriveRepository
import feature.drive.model.stubDrivesListResponse
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DrivesListUseCaseTest {
    private val driveRepository = FakeDriveRepository()
    private val drivesListUseCase = DrivesListUseCase(driveRepository)

    @Test
    fun `Get Drives List Success`() = runTest {
        val result = drivesListUseCase.invoke().getOrNull()
        assertEquals(result, stubDrivesListResponse.drives)
    }

    @Test
    fun `Get Drives List Error`() = runTest {
        driveRepository.setError(true)
        val result = drivesListUseCase.invoke().getOrNull()
        assertNull(result)
    }
}