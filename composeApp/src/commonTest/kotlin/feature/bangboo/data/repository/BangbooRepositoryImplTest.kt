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
    fun `WHEN Get bangboo list success THAN return local DB`() = runTest {
        val result = repository.getBangbooList().first()
        assertEquals(result.size, 1)
    }

    @Test
    fun `WHEN Request bangboo list success THAN return updated DB`() = runTest {
        repository.requestAndUpdateBangbooListDB()
        val result = repository.getBangbooList().first()
        assertEquals(result.size, 3)
    }

    @Test
    fun `GIVEN Bangboo list DB is empty WHEN Get bangboo list THAN Auto request and return updated DB`() =
        runTest {
            bangbooListDao.deleteBangbooList()
            val result = repository.getBangbooList().first()
            assertEquals(result.size, 3)
        }

    @Test
    fun `WHEN Request bangboo list error THAN return local DB`() = runTest {
        httpClient.setError(true)
        val result = repository.getBangbooList().first()
        assertEquals(result.size, 1)
    }

    @Test
    fun `GIVEN bangboo list DB is empty WHEN Request bangboo list error THAN return empty DB`() =
        runTest {
        httpClient.setError(true)
            bangbooListDao.deleteBangbooList()
            val result = repository.getBangbooList().first()
            assertEquals(result.size, 0)
    }

    @Test
    fun `Get Bangboo Detail Success`() = runTest {
        val result = repository.getBangbooDetail(6).getOrNull()
        assertEquals(result?.id, 6)
    }

    @Test
    fun `Get Bangboo Detail Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getBangbooDetail(6).getOrNull()
        assertNull(result)

    }
}