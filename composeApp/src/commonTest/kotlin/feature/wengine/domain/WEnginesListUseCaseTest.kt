/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.domain

import feature.wengine.data.FakeWEngineRepository
import feature.wengine.model.stubWEnginesListResponse
import kotlinx.coroutines.test.runTest
import utils.AgentSpecialty
import utils.ZzzRarity
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class WEnginesListUseCaseTest {

    private val wEngineRepository = FakeWEngineRepository()
    private val wEnginesListUseCase = WEnginesListUseCase(wEngineRepository)


    @Test
    fun `Get W-Engines List Success`() = runTest {
        val result = wEnginesListUseCase.invoke().getOrNull()
        assertEquals(result, stubWEnginesListResponse.wEngines.reversed())
    }

    @Test
    fun `Get W-Engine List Fail`() = runTest {
        wEngineRepository.setError(true)
        val result = wEnginesListUseCase.invoke().getOrNull()
        assertNull(result)
    }

    @Test
    fun `Filter Default`() {
        val result = wEnginesListUseCase.filterWEnginesList(
            wEnginesList = stubWEnginesListResponse.wEngines,
            selectedRarities = emptySet(),
            selectedSpecialties = emptySet(),
        )
        assertEquals(result.size, 2)

    }

    @Test
    fun `Filter Kaboom the Cannon`() {
        val result = wEnginesListUseCase.filterWEnginesList(
            wEnginesList = stubWEnginesListResponse.wEngines,
            selectedRarities = setOf(ZzzRarity.RANK_A),
            selectedSpecialties = setOf(AgentSpecialty.Support),
        )
        assertEquals(result.first().name, "好鬥的阿炮")
        assertEquals(result.size, 1)
    }

    @Test
    fun `Filter Ice-Jade Teapot`() {
        val result = wEnginesListUseCase.filterWEnginesList(
            wEnginesList = stubWEnginesListResponse.wEngines,
            selectedRarities = setOf(ZzzRarity.RANK_S),
            selectedSpecialties = setOf(AgentSpecialty.Stun),
        )
        assertEquals(result.first().name, "玉壺青冰")
        assertEquals(result.size, 1)
    }

    @Test
    fun `Filter Not Match`() {
        val result = wEnginesListUseCase.filterWEnginesList(
            wEnginesList = stubWEnginesListResponse.wEngines,
            selectedRarities = setOf(ZzzRarity.RANK_S),
            selectedSpecialties = setOf(AgentSpecialty.Attack),
        )
        assertEquals(result, emptyList())
    }
}