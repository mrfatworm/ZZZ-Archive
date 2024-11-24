/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.data

import feature.wengine.model.stubWEnginesListResponse
import kotlinx.coroutines.test.runTest
import network.FakeZzzHttp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class WEngineRepositoryImplTest {
    private val httpClient = FakeZzzHttp()
    private val repository = WEngineRepositoryImpl(httpClient)

    @Test
    fun `Get W-Engines List Success`() = runTest {
        val result = repository.getWEnginesList().getOrNull()
        assertEquals(result, stubWEnginesListResponse)
    }

    @Test
    fun `Get W-Engines List Error`() = runTest {
        httpClient.setError(true)
        val result = repository.getWEnginesList().getOrNull()
        assertNull(result)
    }
}