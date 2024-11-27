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
    fun `WHEN Get drives list success THAN return local DB`() = runTest {
        val result = repository.getDrivesList().first()
        assertEquals(result.size, 1)
    }

    @Test
    fun `WHEN Request drives list success THAN return updated DB`() = runTest {
        repository.requestAndUpdateDrivesListDB()
        val result = repository.getDrivesList().first()
        assertEquals(result.size, 2)
    }

    @Test
    fun `GIVEN drives list DB is empty WHEN Get drives list THAN Auto request and return updated DB`() =
        runTest {
            database.deleteDrivesList()
            val result = repository.getDrivesList().first()
            assertEquals(result.size, 2)
        }

    @Test
    fun `WHEN Request drives list error THAN return local DB`() = runTest {
        httpClient.setError(true)
        val result = repository.getDrivesList().first()
        assertEquals(result.size, 1)
    }

    @Test
    fun `GIVEN drives list DB is empty WHEN Request drives list error THAN return empty DB`() =
        runTest {
        httpClient.setError(true)
            database.deleteDrivesList()
            val result = repository.getDrivesList().first()
            assertEquals(result.size, 0)
    }
}