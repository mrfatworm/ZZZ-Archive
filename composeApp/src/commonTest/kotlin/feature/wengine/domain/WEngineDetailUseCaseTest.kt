/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.domain

import feature.wengine.data.FakeWEngineRepository
import feature.wengine.model.stubWEngineDetailResponse
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class WEngineDetailUseCaseTest {

    private val wEngineRepository = FakeWEngineRepository()
    private val wEngineDetailUseCase = WEngineDetailUseCase(wEngineRepository)


    @Test
    fun `Get Agents List Success`() = runTest {
        val result = wEngineDetailUseCase.invoke(1).getOrNull()
        assertEquals(result, stubWEngineDetailResponse)
    }

    @Test
    fun `Get Agents List Fail`() = runTest {
        wEngineRepository.setError(true)
        val result = wEngineDetailUseCase.invoke(1).getOrNull()
        assertNull(result)
    }
}