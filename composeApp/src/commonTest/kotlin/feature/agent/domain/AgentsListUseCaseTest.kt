/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.domain

import feature.agent.data.repository.FakeAgentRepository
import feature.agent.model.Faction
import feature.agent.model.stubAgentsList
import feature.setting.domain.FakeLanguageUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull


class AgentsListUseCaseTest {

    private val agentRepository = FakeAgentRepository()
    private val languageUseCase = FakeLanguageUseCase()
    private val agentsListUseCase = AgentsListUseCase(agentRepository, languageUseCase)


    @Test
    fun `Get agents list success`() = runTest {
        val result = agentsListUseCase.invoke().first()
        assertEquals(stubAgentsList, result)
    }

    @Test
    fun `Request agents list success`() = runTest {
        val result = agentsListUseCase.updateAgentsList().getOrNull()
        assertEquals(Unit, result)
    }

    @Test
    fun `Request agents list error`() = runTest {
        agentRepository.setError(true)
        val result = agentsListUseCase.updateAgentsList().getOrNull()
        assertNull(result)
    }

    @Test
    fun `Get factions list`() {
        val result = agentsListUseCase.getFactionsList(stubAgentsList)
        assertEquals(listOf(Faction(1), Faction(2)), result)
    }

    @Test
    fun `Filter default`() {
        val result = agentsListUseCase.filterAgentsList(
            agentsList = stubAgentsList,
            selectedRarities = emptySet(),
            selectedAttributes = emptySet(),
            selectedSpecialties = emptySet(),
            selectedFactionId = 0
        )
        assertEquals(3, result.size)

    }

    @Test
    fun `Filter Nekomiya`() {
        val result = agentsListUseCase.filterAgentsList(
            agentsList = stubAgentsList,
            selectedRarities = setOf(ZzzRarity.RARITY_S),
            selectedAttributes = setOf(AgentAttribute.Physical),
            selectedSpecialties = setOf(AgentSpecialty.Attack),
            selectedFactionId = 0
        )
        assertEquals("貓又", result.first().name)
        assertEquals(1, result.size)
    }

    @Test
    fun `Filter faction Gentle House`() {
        val result = agentsListUseCase.filterAgentsList(
            agentsList = stubAgentsList,
            selectedRarities = emptySet(),
            selectedAttributes = emptySet(),
            selectedSpecialties = emptySet(),
            selectedFactionId = 1
        )
        assertEquals("貓又", result.first().name)
        assertEquals(2, result.size)
    }

    @Test
    fun `Filter faction Gentle House and Nekomiya`() {
        val result = agentsListUseCase.filterAgentsList(
            agentsList = stubAgentsList,
            selectedRarities = setOf(ZzzRarity.RARITY_S),
            selectedAttributes = setOf(AgentAttribute.Physical),
            selectedSpecialties = setOf(AgentSpecialty.Attack),
            selectedFactionId = 1
        )
        assertEquals("貓又", result.first().name)
        assertEquals(1, result.size)
    }

    @Test
    fun `Filter Colin`() {
        val result = agentsListUseCase.filterAgentsList(
            agentsList = stubAgentsList,
            selectedRarities = setOf(ZzzRarity.RARITY_A),
            selectedAttributes = setOf(AgentAttribute.Physical),
            selectedSpecialties = setOf(AgentSpecialty.Attack),
            selectedFactionId = 0
        )
        assertEquals("可琳", result.first().name)
        assertEquals(1, result.size)
    }

    @Test
    fun `Filter not match`() {
        val result = agentsListUseCase.filterAgentsList(
            agentsList = stubAgentsList,
            selectedRarities = setOf(ZzzRarity.RARITY_S),
            selectedAttributes = setOf(AgentAttribute.Ether),
            selectedSpecialties = setOf(AgentSpecialty.Support),
            selectedFactionId = 1
        )
        assertEquals(emptyList(), result)
    }
}