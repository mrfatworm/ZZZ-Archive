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
        assertEquals(stubWEnginesList, result)
    }

    @Test
    fun `Request W-Engines list success`() = runTest {
        val result = wEnginesListUseCase.updateWEnginesList().getOrNull()
        assertEquals(Unit, result)
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
        assertEquals(2, result.size)

    }

    @Test
    fun `Filter Kaboom the Cannon`() {
        val result = wEnginesListUseCase.filterWEnginesList(
            wEnginesList = stubWEnginesList,
            selectedRarities = setOf(ZzzRarity.RARITY_A),
            selectedSpecialties = setOf(AgentSpecialty.Support),
        )
        assertEquals("好鬥的阿炮", result.first().name)
        assertEquals(1, result.size)
    }

    @Test
    fun `Filter Ice-Jade Teapot`() {
        val result = wEnginesListUseCase.filterWEnginesList(
            wEnginesList = stubWEnginesList,
            selectedRarities = setOf(ZzzRarity.RARITY_S),
            selectedSpecialties = setOf(AgentSpecialty.Stun),
        )
        assertEquals("玉壺青冰", result.first().name)
        assertEquals(1, result.size)
    }

    @Test
    fun `Filter not match`() {
        val result = wEnginesListUseCase.filterWEnginesList(
            wEnginesList = stubWEnginesList,
            selectedRarities = setOf(ZzzRarity.RARITY_S),
            selectedSpecialties = setOf(AgentSpecialty.Attack),
        )
        assertEquals(emptyList(), result)
    }
}