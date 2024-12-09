/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.domain

import feature.agent.data.repository.FakeAgentRepository
import feature.agent.model.Faction
import feature.agent.model.stubAgentsList
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
    private val agentsListUseCase = AgentsListUseCase(agentRepository)


    @Test
    fun `Get agents list success`() = runTest {
        val result = agentsListUseCase.invoke().first()
        assertEquals(result, stubAgentsList)
    }

    @Test
    fun `Request agents list success`() = runTest {
        val result = agentsListUseCase.updateAgentsList().getOrNull()
        assertEquals(result, Unit)
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
        assertEquals(result, listOf(Faction(1), Faction(2)))
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
        assertEquals(result.size, 3)

    }

    @Test
    fun `Filter Nekomiya`() {
        val result = agentsListUseCase.filterAgentsList(
            agentsList = stubAgentsList,
            selectedRarities = setOf(ZzzRarity.RANK_S),
            selectedAttributes = setOf(AgentAttribute.Physical),
            selectedSpecialties = setOf(AgentSpecialty.Attack),
            selectedFactionId = 0
        )
        assertEquals(result.first().name, "貓又")
        assertEquals(result.size, 1)
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
        assertEquals(result.first().name, "貓又")
        assertEquals(result.size, 2)
    }

    @Test
    fun `Filter faction Gentle House and Nekomiya`() {
        val result = agentsListUseCase.filterAgentsList(
            agentsList = stubAgentsList,
            selectedRarities = setOf(ZzzRarity.RANK_S),
            selectedAttributes = setOf(AgentAttribute.Physical),
            selectedSpecialties = setOf(AgentSpecialty.Attack),
            selectedFactionId = 1
        )
        assertEquals(result.first().name, "貓又")
        assertEquals(result.size, 1)
    }

    @Test
    fun `Filter Colin`() {
        val result = agentsListUseCase.filterAgentsList(
            agentsList = stubAgentsList,
            selectedRarities = setOf(ZzzRarity.RANK_A),
            selectedAttributes = setOf(AgentAttribute.Physical),
            selectedSpecialties = setOf(AgentSpecialty.Attack),
            selectedFactionId = 0
        )
        assertEquals(result.first().name, "可琳")
        assertEquals(result.size, 1)
    }

    @Test
    fun `Filter not match`() {
        val result = agentsListUseCase.filterAgentsList(
            agentsList = stubAgentsList,
            selectedRarities = setOf(ZzzRarity.RANK_S),
            selectedAttributes = setOf(AgentAttribute.Ether),
            selectedSpecialties = setOf(AgentSpecialty.Support),
            selectedFactionId = 1
        )
        assertEquals(result, emptyList())
    }
}