/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.data.repository

import feature.wengine.data.database.FakeWEnginesListDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import kotlin.test.Test
import kotlin.test.assertEquals

class WEngineRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val database = FakeWEnginesListDao()
    private val repository = WEngineRepositoryImpl(httpClient, database)
    // Remote: 2 W-Engines, Local: 1 W-Engine

    @Test
    fun `WHEN Get W-Engines list success THEN return local DB`() = runTest {
        val result = repository.getWEnginesList("").first()
        assertEquals(result.size, 1)
    }

    @Test
    fun `WHEN Request W-Engines list success THEN return updated DB`() = runTest {
        repository.requestAndUpdateWEnginesListDB("")
        val result = repository.getWEnginesList("").first()
        assertEquals(result.size, 2)
    }

    @Test
    fun `GIVEN W-Engines list DB is empty WHEN Get W-Engines list THEN Auto request and return updated DB`() =
        runTest {
            database.deleteWEnginesList()
            val result = repository.getWEnginesList("").first()
            assertEquals(result.size, 2)
        }

    @Test
    fun `WHEN Request W-Engines list error THEN return local DB`() = runTest {
        httpClient.setError(true)
        val result = repository.getWEnginesList("").first()
        assertEquals(result.size, 1)
    }

    @Test
    fun `GIVEN W-Engines list DB is empty WHEN Request W-Engines list error THEN return empty DB`() =
        runTest {
            httpClient.setError(true)
            database.deleteWEnginesList()
            val result = repository.getWEnginesList("").first()
            assertEquals(result.size, 0)
        }
}