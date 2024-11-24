/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.agent.domain.AgentsListUseCase
import feature.bangboo.domain.BangbooListUseCase
import feature.cover.data.CoverImageRepository
import feature.drive.data.DriveRepository
import feature.home.model.HomeState
import feature.home.model.pixivTagDropdownItems
import feature.news.domain.OfficialNewsUseCase
import feature.pixiv.data.PixivRepository
import feature.setting.data.SettingsRepository
import feature.wengine.data.WEngineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import root.data.BannerRepository
import utils.ZzzResult

class HomeViewModel(
    private val bannerRepository: BannerRepository,
    private val coverImageRepository: CoverImageRepository,
    private val pixivRepository: PixivRepository,
    private val newsUseCase: OfficialNewsUseCase,
    private val agentsListUseCase: AgentsListUseCase,
    private val wEngineRepository: WEngineRepository,
    private val bangbooListUseCase: BangbooListUseCase,
    private val driveRepository: DriveRepository,
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
        when (val result = bannerRepository.getBanner()) {
            is ZzzResult.Success -> {
                if (result.data.id > ignoreBannerId) {
                    _uiState.update { state ->
                        state.copy(banner = result.data)
                    }
                }
            }

            is ZzzResult.Error -> {
                println("get banner error: ${result.exception}")
            }
        }
    }

    private suspend fun fetchBannerImage() {
        when (val result = coverImageRepository.getImageBanner()) {
            is ZzzResult.Success -> {
                _uiState.update { state ->
                    state.copy(imageBanner = result.data)
                }
            }

            is ZzzResult.Error -> {
                println("get banner error: ${result.exception}")
            }
        }
    }

    private suspend fun fetchZzzOfficialNewsEveryTenMinutes() {
        newsUseCase.getNewsPeriodically(10, 5).collect { result ->
            when (result) {
                is ZzzResult.Success -> {
                    _uiState.update { state ->
                        state.copy(newsList = newsUseCase.convertToOfficialNewsState(result.data))
                    }
                }

                is ZzzResult.Error -> {
                    println("get news error: ${result.exception}")
                }
            }
        }
    }

    suspend fun fetchPixivTopic(zzzTag: String = pixivTagDropdownItems.first().tagOnPixiv) {
        when (val result = pixivRepository.getZzzTopic(zzzTag)) {
            is ZzzResult.Success -> {
                _uiState.update { state ->
                    state.copy(pixivPuppiesList = result.data.getPopularArticles())
                }
            }

            is ZzzResult.Error -> {
                println("get pixiv error: ${result.exception}")
            }
        }
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
        when (val result = wEngineRepository.getWEnginesList()) {
            is ZzzResult.Success -> {
                _uiState.update { state ->
                    state.copy(wEnginesList = result.data.getWEnginesNewToOld())
                }
            }

            is ZzzResult.Error -> {
                println("get w-engines error: ${result.exception}")
            }
        }
    }

    private suspend fun fetchBangbooList() {
        val result = bangbooListUseCase.invoke()
        result.fold(onSuccess = { bangbooList ->
            _uiState.update {
                it.copy(bangbooList = bangbooList)
            }
        }, onFailure = {
            println("get bangboos error: ${it.message}")
        })
    }

    private suspend fun fetchDrivesList() {
        when (val result = driveRepository.getDrivesList()) {
            is ZzzResult.Success -> {
                _uiState.update { state ->
                    state.copy(drivesList = result.data.getDrivesNewToOld())
                }
            }

            is ZzzResult.Error -> {
                println("get drives error: ${result.exception}")
            }
        }
    }
}