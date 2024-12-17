/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.data

import feature.drive.data.database.FakeDrivesListDao
import feature.drive.data.respository.DriveRepositoryImpl
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import kotlin.test.Test
import kotlin.test.assertEquals

class DriveRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val database = FakeDrivesListDao()
    private val repository = DriveRepositoryImpl(httpClient, database)
    // Remote: 2 Drive Discs, Local: 1 Drive Disc

    @Test
    fun `WHEN Get drives list success THEN return local DB`() = runTest {
        val result = repository.getDrivesList("").first()
        assertEquals(1, result.size)
    }

    @Test
    fun `WHEN Request drives list success THEN return updated DB`() = runTest {
        repository.requestAndUpdateDrivesListDB("")
        val result = repository.getDrivesList("").first()
        assertEquals(2, result.size)
    }

    @Test
    fun `GIVEN drives list DB is empty WHEN Get drives list THEN Auto request and return updated DB`() =
        runTest {
            database.deleteDrivesList()
            val result = repository.getDrivesList("").first()
            assertEquals(2, result.size)
        }

    @Test
    fun `WHEN Request drives list error THEN return local DB`() = runTest {
        httpClient.setError(true)
        val result = repository.getDrivesList("").first()
        assertEquals(1, result.size)
    }

    @Test
    fun `GIVEN drives list DB is empty WHEN Request drives list error THEN return empty DB`() =
        runTest {
            httpClient.setError(true)
            database.deleteDrivesList()
            val result = repository.getDrivesList("").first()
            assertEquals(0, result.size)
        }
}