/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.agent.domain.AgentsListUseCase
import feature.agent.model.AgentListItem
import feature.bangboo.domain.BangbooListUseCase
import feature.bangboo.model.BangbooListItem
import feature.banner.domain.BannerUseCase
import feature.cover_image.domain.CoverImageUseCase
import feature.drive.data.database.DrivesListItemEntity
import feature.drive.domain.DrivesListUseCase
import feature.home.model.pixivTagDropdownItems
import feature.news.domain.OfficialNewsUseCase
import feature.pixiv.domain.PixivUseCase
import feature.setting.data.SettingsRepository
import feature.wengine.domain.WEnginesListUseCase
import feature.wengine.model.WEnginesListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bannerUseCase: BannerUseCase,
    private val coverImageUseCase: CoverImageUseCase,
    private val pixivUseCase: PixivUseCase,
    private val newsUseCase: OfficialNewsUseCase,
    private val agentsListUseCase: AgentsListUseCase,
    private val wEnginesListUseCase: WEnginesListUseCase,
    private val bangbooListUseCase: BangbooListUseCase,
    private val drivesListUseCase: DrivesListUseCase,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private var ignoreBannerId = 0
    private var _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()
    private var _agentsListState = MutableStateFlow<List<AgentListItem>>(emptyList())
    val agentsListState = _agentsListState.asStateFlow()
    private var _wEnginesListState = MutableStateFlow<List<WEnginesListItem>>(emptyList())
    val wEnginesListState = _wEnginesListState.asStateFlow()
    private var _bangbooListState = MutableStateFlow<List<BangbooListItem>>(emptyList())
    val bangbooListState = _bangbooListState.asStateFlow()
    private var _driveListState = MutableStateFlow<List<DrivesListItemEntity>>(emptyList())
    val driveListState = _driveListState.asStateFlow()


    init {
        ignoreBannerId = settingsRepository.getBannerIgnoreId()
        viewModelScope.launch {
            launch { fetchBanner() }
            launch { fetchBannerImage() }
            launch { fetchZzzOfficialNewsEveryTenMinutes() }
            launch { fetchPixivTopic() }
            launch { observeAgentsList() }
            launch { observeWEnginesList() }
            launch { observeBangbooList() }
            launch { observeDrivesList() }
        }
    }

    fun closeBannerAndIgnoreId(id: Int) {
        ignoreBannerId = id
        settingsRepository.setBannerIgnoreId(id)
        _uiState.update { state ->
            state.copy(banner = null)
        }
    }

    private suspend fun fetchBanner() {
        val result = bannerUseCase.invoke()
        result.fold(onSuccess = { banner ->
            if (banner.id != ignoreBannerId) {
                _uiState.update {
                    it.copy(banner = banner)
                }
            }
        }, onFailure = {
            println("get banner error: ${it.message}")
        })
    }

    private suspend fun fetchBannerImage() {
        val result = coverImageUseCase.invoke()
        result.fold(onSuccess = { coverImage ->
            _uiState.update {
                it.copy(coverImage = coverImage)
            }
        }, onFailure = {
            println("get banner image error: ${it.message}")
        })
    }

    private suspend fun fetchZzzOfficialNewsEveryTenMinutes() {
        newsUseCase.getNewsPeriodically(10, 5).collect { result ->
            result.fold(onSuccess = { newsList ->
                _uiState.update { state ->
                    state.copy(newsList = newsUseCase.convertToOfficialNewsState(newsList))
                }
            }, onFailure = {
                println("get news error: ${it.message}")
            })
        }
    }

    suspend fun fetchPixivTopic(zzzTag: String = pixivTagDropdownItems.first().tagOnPixiv) {
        val result = pixivUseCase.invoke(zzzTag)
        result.fold(onSuccess = { pixivTopic ->
            _uiState.update {
                it.copy(pixivTopics = pixivTopic)
            }
        }, onFailure = {
            println("get pixiv topic error: ${it.message}")
        })
    }

    private suspend fun observeAgentsList() {
        agentsListUseCase.invoke().collect { agentsList ->
            _agentsListState.value = agentsList
        }
    }

    private suspend fun observeWEnginesList() {
        wEnginesListUseCase.invoke().collect { wEnginesList ->
            _wEnginesListState.value = wEnginesList
        }
    }

    private suspend fun observeBangbooList() {
        bangbooListUseCase.invoke().collect { bangbooList ->
            _bangbooListState.value = bangbooList
        }
    }

    private suspend fun observeDrivesList() {
        drivesListUseCase.invoke().collect { drivesList ->
            _driveListState.value = drivesList
        }
    }
}