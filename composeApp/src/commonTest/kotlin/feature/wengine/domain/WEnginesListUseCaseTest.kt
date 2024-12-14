/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.domain

import feature.setting.domain.FakeLanguageUseCase
import feature.wengine.data.repository.FakeWEngineRepository
import feature.wengine.model.stubWEnginesList
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import utils.AgentSpecialty
import utils.ZzzRarity
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class WEnginesListUseCaseTest {

    private val wEngineRepository = FakeWEngineRepository()
    private val languageUseCase = FakeLanguageUseCase()
    private val wEnginesListUseCase = WEnginesListUseCase(wEngineRepository, languageUseCase)

    @Test
    fun `Get W-Engines list success`() = runTest {
        val result = wEnginesListUseCase.invoke().first()
        assertEquals(result, stubWEnginesList)
    }

    @Test
    fun `Request W-Engines list success`() = runTest {
        val result = wEnginesListUseCase.updateWEnginesList().getOrNull()
        assertEquals(result, Unit)
    }

    @Test
    fun `Request W-Engines list error`() = runTest {
        wEngineRepository.setError(true)
        val result = wEnginesListUseCase.updateWEnginesList().getOrNull()
        assertNull(result)
    }

    @Test
    fun `Filter default`() {
        val result = wEnginesListUseCase.filterWEnginesList(
            wEnginesList = stubWEnginesList,
            selectedRarities = emptySet(),
            selectedSpecialties = emptySet(),
        )
        assertEquals(result.size, 2)

    }

    @Test
    fun `Filter Kaboom the Cannon`() {
        val result = wEnginesListUseCase.filterWEnginesList(
            wEnginesList = stubWEnginesList,
            selectedRarities = setOf(ZzzRarity.RANK_A),
            selectedSpecialties = setOf(AgentSpecialty.Support),
        )
        assertEquals(result.first().name, "好鬥的阿炮")
        assertEquals(result.size, 1)
    }

    @Test
    fun `Filter Ice-Jade Teapot`() {
        val result = wEnginesListUseCase.filterWEnginesList(
            wEnginesList = stubWEnginesList,
            selectedRarities = setOf(ZzzRarity.RANK_S),
            selectedSpecialties = setOf(AgentSpecialty.Stun),
        )
        assertEquals(result.first().name, "玉壺青冰")
        assertEquals(result.size, 1)
    }

    @Test
    fun `Filter not match`() {
        val result = wEnginesListUseCase.filterWEnginesList(
            wEnginesList = stubWEnginesList,
            selectedRarities = setOf(ZzzRarity.RANK_S),
            selectedSpecialties = setOf(AgentSpecialty.Attack),
        )
        assertEquals(result, emptyList())
    }
}