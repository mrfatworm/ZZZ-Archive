/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.model

import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity


data class AgentsListState(
    val agentsList: List<AgentListItem> = emptyList(),
    val filteredAgentsList: List<AgentListItem> = emptyList(),
    val factionsList: List<Faction> = emptyList(),
    val selectedRarity: Set<ZzzRarity> = emptySet(),
    val selectedAttributes: Set<AgentAttribute> = emptySet(),
    val selectedSpecialties: Set<AgentSpecialty> = emptySet(),
    val selectedFactionId: Int = 0,
    val isLoading: Boolean = true,
    val error: String? = null
)