package feature.hoyolab.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import feature.hoyolab.domain.HoYoLabAgentUseCase
import feature.hoyolab.domain.HoYoLabPreferenceUseCase
import feature.hoyolab.model.agent.EquipPlanProperty
import feature.hoyolab.model.agent.MyAgentDetailEquipPlan
import feature.hoyolab.model.agent.MyAgentDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ui.navigation.Screen

class MyAgentDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val hoYoLabAgentUseCase: HoYoLabAgentUseCase,
    private val hoYoLabPreferenceUseCase: HoYoLabPreferenceUseCase
) : ViewModel() {
    private val agentId: Int = checkNotNull(savedStateHandle.toRoute<Screen.MyAgentDetail>().id)

    private val _uiState = MutableStateFlow(MyAgentDetailState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            updateMyAgentDetailFromHoYoLab()
            getDefaultUid()
        }
    }

    fun onAction(action: MyAgentDetailAction) {
        when (action) {
            MyAgentDetailAction.ClickBack -> {}

            is MyAgentDetailAction.ConfirmEditImage -> {
                _uiState.update { state ->
                    state.copy(
                        showUid = action.showUid,
                        isCustomImage = action.isCustom,
                        customImgUrl = if (action.isCustom) action.imageUrl else "",
                        customImgAuthor = if (action.isCustom) action.author else "",
                        hasBlurBackground = if (action.isCustom) action.hasBlurBackground else false,
                        adjustMode = action.isCustom
                    )
                }
            }

            MyAgentDetailAction.AdjustImageDone -> {
                _uiState.update { state ->
                    state.copy(adjustMode = false)
                }
            }
        }
    }

    private suspend fun updateMyAgentDetailFromHoYoLab() {
        hoYoLabAgentUseCase.getAgentDetail(agentId).fold(onSuccess = {
            val planProperties =
                if (it.equipPlanInfo is MyAgentDetailEquipPlan.MyAgentEquipPlan &&
                    it.equipPlanInfo.planEffectivePropertyList.isNotEmpty()
                ) {
                    it.equipPlanInfo.planEffectivePropertyList.map { property ->
                        EquipPlanProperty(
                            id = property.id,
                            name = property.name,
                            fullName = property.fullName,
                            systemId = property.systemId,
                            isSelect = property.isSelect
                        )
                    }
                } else {
                    if (it.equipPlanInfo is MyAgentDetailEquipPlan.MyAgentEquipPlan) {
                        it.equipPlanInfo.gameDefaultPropertyList.map { property ->
                            EquipPlanProperty(
                                id = property.id,
                                name = property.name,
                                fullName = property.fullName,
                                systemId = property.systemId,
                                isSelect = property.isSelect
                            )
                        }
                    } else {
                        emptyList()
                    }
                }
            _uiState.update { state ->
                state.copy(agentDetail = it, planProperties = planProperties)
            }
        }, onFailure = {
            _uiState.update { state ->
                state.copy(errorMessage = it.message ?: "Unknown error")
            }
        })
    }

    private suspend fun getDefaultUid() {
        hoYoLabPreferenceUseCase.getDefaultHoYoLabAccountUid().collect {
            _uiState.update { state ->
                state.copy(uid = it.toString())
            }
        }
    }
}
