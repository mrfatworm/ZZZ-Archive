package feature.agent.presentation

import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity


sealed interface AgentsListAction {
    data class ChangeRarityFilter(val rarities: Set<ZzzRarity>) : AgentsListAction
    data class ChangeAttributeFilter(val attributes: Set<AgentAttribute>) : AgentsListAction
    data class ChangeSpecialtyFilter(val specialties: Set<AgentSpecialty>) : AgentsListAction
    data class ChangeFactionFilter(val factionId: Int) : AgentsListAction
    data class ClickAgent(val agentId: Int) : AgentsListAction
    data object ClickBack : AgentsListAction
}