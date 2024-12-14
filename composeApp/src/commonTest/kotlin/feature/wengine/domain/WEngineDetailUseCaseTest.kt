/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.domain

import feature.setting.domain.FakeLanguageUseCase
import feature.wengine.data.repository.FakeWEngineRepository
import feature.wengine.model.stubWEngineDetail
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class WEngineDetailUseCaseTest {

    private val wEngineRepository = FakeWEngineRepository()
    private val languageUseCase = FakeLanguageUseCase()
    private val wEngineDetailUseCase = WEngineDetailUseCase(wEngineRepository, languageUseCase)


    @Test
    fun `Get W-Engines list success`() = runTest {
        val result = wEngineDetailUseCase.invoke(1).getOrNull()
        assertEquals(result, stubWEngineDetail)
    }

    @Test
    fun `Get W-Engines list error`() = runTest {
        wEngineRepository.setError(true)
        val result = wEngineDetailUseCase.invoke(1).getOrNull()
        assertNull(result)
    }
}