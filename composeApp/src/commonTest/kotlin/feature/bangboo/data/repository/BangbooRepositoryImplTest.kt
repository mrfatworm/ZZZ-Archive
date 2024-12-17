/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.data.repository

import feature.bangboo.data.database.FakeBangbooListDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BangbooRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val bangbooListDao = FakeBangbooListDao()
    private val repository = BangbooRepositoryImpl(httpClient, bangbooListDao)
    // Remote: 3 bangboo, Local: 1 bangboo

    @Test
    fun `WHEN Get bangboo list success THEN return local DB`() = runTest {
        val result = repository.getBangbooList("").first()
        assertEquals(1, result.size)
    }

    @Test
    fun `WHEN Request bangboo list success THEN return updated DB`() = runTest {
        repository.requestAndUpdateBangbooListDB("")
        val result = repository.getBangbooList("").first()
        assertEquals(3, result.size)
    }

    @Test
    fun `GIVEN Bangboo list DB is empty WHEN Get bangboo list THEN Auto request and return updated DB`() =
        runTest {
            bangbooListDao.deleteBangbooList()
            val result = repository.getBangbooList("").first()
            assertEquals(3, result.size)
        }

    @Test
    fun `WHEN Request bangboo list error THEN return local DB`() = runTest {
        httpClient.setError(true)
        val result = repository.getBangbooList("").first()
        assertEquals(1, result.size)
    }

    @Test
    fun `GIVEN bangboo list DB is empty WHEN Request bangboo list error THEN return empty DB`() =
        runTest {
            httpClient.setError(true)
            bangbooListDao.deleteBangbooList()
            val result = repository.getBangbooList("").first()
            assertEquals(0, result.size)
        }

    @Test
    fun `Get Bangboo Detail Success`() = runTest {
        val result = repository.getBangbooDetail(6, "").getOrNull()
        assertEquals(6, result?.id)
    }

    @Test
    fun `Get Bangboo Detail Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getBangbooDetail(6, "").getOrNull()
        assertNull(result)

    }
}