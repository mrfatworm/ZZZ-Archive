package feature.bangboo.presentation

import utils.AgentAttribute
import utils.ZzzRarity


sealed interface BangbooListAction {
    data class OnRarityFilterChanged(val rarities: Set<ZzzRarity>) : BangbooListAction
    data class OnAttributeFilterChanged(val attributes: Set<AgentAttribute>) : BangbooListAction
    data class OnBangbooClick(val bangbooId: Int) : BangbooListAction
    data object OnBackClick : BangbooListAction
    data object OnRetry : BangbooListAction
}