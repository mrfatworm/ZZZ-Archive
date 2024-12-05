package feature.wengine.presentation

import utils.AgentSpecialty
import utils.ZzzRarity


sealed interface WEnginesListAction {
    data class ChangeRarityFilter(val rarities: Set<ZzzRarity>) : WEnginesListAction
    data class ChangeSpecialtyFilter(val specialties: Set<AgentSpecialty>) : WEnginesListAction
    data class ClickWEngine(val wEngineId: Int) : WEnginesListAction
    data object ClickBack : WEnginesListAction
}