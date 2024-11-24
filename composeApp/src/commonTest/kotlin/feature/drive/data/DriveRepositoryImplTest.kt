/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.data

import feature.drive.model.stubDrivesListResponse
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DriveRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val repository = DriveRepositoryImpl(httpClient)

    @Test
    fun `Get Drives List Success`() = runTest {
        val result = repository.getDrivesList().getOrNull()
        assertEquals(result, stubDrivesListResponse)
    }

    @Test
    fun `Get Drives List Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getDrivesList().getOrNull()
        assertNull(result)
    }
}