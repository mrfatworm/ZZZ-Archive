/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.data

import feature.bangboo.model.stubBangbooListResponse
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BangbooRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val repository = BangbooRepositoryImpl(httpClient)

    @Test
    fun `Get Bangboo List Success`() = runTest {
        val result = repository.getBangbooList().getOrNull()
        assertEquals(result, stubBangbooListResponse)
    }

    @Test
    fun `Get Bangboo List Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getBangbooList().getOrNull()
        assertNull(result)
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