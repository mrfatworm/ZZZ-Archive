/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.domain

import feature.bangboo.data.FakeBangbooRepository
import feature.bangboo.model.stubBangbooDetailResponse
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class BangbooDetailUseCaseTest {

    private val bangbooRepository = FakeBangbooRepository()
    private val bangbooDetailUseCase = BangbooDetailUseCase(bangbooRepository)


    @Test
    fun `Get Agents List Success`() = runTest {
        val result = bangbooDetailUseCase.invoke(1).getOrNull()
        assertEquals(result, stubBangbooDetailResponse)
    }

    @Test
    fun `Get Agents List Fail`() = runTest {
        bangbooRepository.setError(true)
        val result = bangbooDetailUseCase.invoke(1).getOrNull()
        assertNull(result)
    }
}