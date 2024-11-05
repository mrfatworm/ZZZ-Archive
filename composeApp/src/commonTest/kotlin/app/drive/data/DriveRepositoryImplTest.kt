/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.drive.data

import app.drive.model.stubDrivesListResponse
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import utils.ZzzResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DriveRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val repository = DriveRepositoryImpl(httpClient)

    @Test
    fun `Get Drives List Success`() = runTest {
        val result = repository.getDrivesList() as ZzzResult.Success
        assertEquals(result.data, stubDrivesListResponse)
    }

    @Test
    fun `Get Drives List Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getDrivesList() as ZzzResult.Error
        assertTrue(result.exception.instanceOf(Exception::class))
    }
}