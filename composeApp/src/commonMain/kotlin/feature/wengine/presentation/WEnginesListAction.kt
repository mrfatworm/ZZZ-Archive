package feature.wengine.presentation

import utils.AgentSpecialty
import utils.ZzzRarity


sealed interface WEnginesListAction {
    data class OnRarityFilterChanged(val rarities: Set<ZzzRarity>) : WEnginesListAction
    data class OnSpecialtyFilterChanged(val specialties: Set<AgentSpecialty>) : WEnginesListAction
    data class OnWEngineClick(val wEngineId: Int) : WEnginesListAction
    data object OnBackClick : WEnginesListAction
}