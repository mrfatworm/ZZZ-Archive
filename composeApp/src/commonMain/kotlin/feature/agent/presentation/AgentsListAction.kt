package feature.agent.presentation

import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity


sealed interface AgentsListAction {
    data class OnRarityFilterChanged(val rarities: Set<ZzzRarity>) : AgentsListAction
    data class OnAttributeFilterChanged(val attributes: Set<AgentAttribute>) : AgentsListAction
    data class OnSpecialtyFilterChanged(val specialties: Set<AgentSpecialty>) : AgentsListAction
    data class OnFactionFilterChanged(val factionId: Int) : AgentsListAction
    data class OnAgentClick(val agentId: Int) : AgentsListAction
    data object OnBackClick : AgentsListAction
}