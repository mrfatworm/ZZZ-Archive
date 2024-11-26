/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.domain

import feature.agent.data.repository.AgentRepository
import feature.agent.model.AgentListItem
import feature.agent.model.Faction
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity


class AgentsListUseCase(
    private val agentRepository: AgentRepository
) {

    suspend fun invoke() = agentRepository.getAgentsList()
    suspend fun updateAgentsList() = agentRepository.requestAndUpdateAgentsListDB()

    fun getFactionsList(agentsList: List<AgentListItem>): List<Faction> {
        val maxFactionId = agentsList.maxOfOrNull { it.factionId } ?: 0
        val factionsList = List(maxFactionId) { index -> Faction(index + 1) }
        return factionsList
    }

    fun filterAgentsList(
        agentsList: List<AgentListItem>,
        selectedRarities: Set<ZzzRarity>,
        selectedAttributes: Set<AgentAttribute>,
        selectedSpecialties: Set<AgentSpecialty>,
        selectedFactionId: Int
    ): List<AgentListItem> {
        val filteredAgents = agentsList.filter { agent ->
            val matchRarity =
                selectedRarities.isEmpty() || selectedRarities.any { it == agent.rarity }
            val matchAttribute =
                selectedAttributes.isEmpty() || selectedAttributes.any { it == agent.attribute }
            val matchSpecialty =
                selectedSpecialties.isEmpty() || selectedSpecialties.any { it == agent.specialty }
            val matchFaction = selectedFactionId == 0 || selectedFactionId == agent.factionId

            matchRarity && matchAttribute && matchSpecialty && matchFaction
        }
        return filteredAgents
    }
}