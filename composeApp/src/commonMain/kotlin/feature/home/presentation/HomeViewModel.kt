/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.agent.domain.AgentsListUseCase
import feature.bangboo.domain.BangbooListUseCase
import feature.banner.domain.BannerUseCase
import feature.cover_image.domain.CoverImageUseCase
import feature.drive.domain.DrivesListUseCase
import feature.home.model.pixivTagDropdownItems
import feature.news.domain.OfficialNewsUseCase
import feature.pixiv.domain.PixivUseCase
import feature.setting.data.SettingsRepository
import feature.wengine.domain.WEnginesListUseCase
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

    private var _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()
    private var ignoreBannerId = 0

    init {
        ignoreBannerId = settingsRepository.getBannerIgnoreId()
        viewModelScope.launch {
            launch { fetchBanner() }
            launch { fetchBannerImage() }
            launch { fetchZzzOfficialNewsEveryTenMinutes() }
            launch { fetchPixivTopic() }
            launch { fetchAgentsList() }
            launch { fetchWEnginesList() }
            launch { fetchBangbooList() }
            launch { fetchDrivesList() }
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


    private suspend fun fetchAgentsList() {
        val result = agentsListUseCase.invoke()
        result.fold(onSuccess = { agentsList ->
            _uiState.update {
                it.copy(agentsList = agentsList)
            }
        }, onFailure = {
            println("get agents error: ${it.message}")
        })
    }

    private suspend fun fetchWEnginesList() {
        val result = wEnginesListUseCase.invoke()
        result.fold(onSuccess = { wEnginesList ->
            _uiState.update {
                it.copy(wEnginesList = wEnginesList)
            }
        }, onFailure = {
            println("get wEngines error: ${it.message}")
        })
    }

    private suspend fun fetchBangbooList() {
        val result = bangbooListUseCase.invoke()
        result.fold(onSuccess = { bangbooList ->
            _uiState.update {
                it.copy(bangbooList = bangbooList)
            }
        }, onFailure = {
            println("get bangboo error: ${it.message}")
        })
    }

    private suspend fun fetchDrivesList() {
        val result = drivesListUseCase.invoke()
        result.fold(onSuccess = { drivesList ->
            _uiState.update {
                it.copy(drivesList = drivesList)
            }
        }, onFailure = {
            println("get drives error: ${it.message}")
        })
    }
}