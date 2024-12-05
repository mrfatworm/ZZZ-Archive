package feature.bangboo.presentation

import utils.AgentAttribute
import utils.ZzzRarity


sealed interface BangbooListAction {
    data class ChangeRarityFilter(val rarities: Set<ZzzRarity>) : BangbooListAction
    data class ChangeAttributeFilter(val attributes: Set<AgentAttribute>) : BangbooListAction
    data class ClickBangboo(val bangbooId: Int) : BangbooListAction
    data object ClickBack : BangbooListAction
}