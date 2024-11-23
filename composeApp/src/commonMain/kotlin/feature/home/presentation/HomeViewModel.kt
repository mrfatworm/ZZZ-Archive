/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.agent.data.AgentRepository
import feature.bangboo.data.BangbooRepository
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
    private val agentRepository: AgentRepository,
    private val wEngineRepository: WEngineRepository,
    private val bangbooRepository: BangbooRepository,
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
        when (val result = agentRepository.getAgentsList()) {
            is ZzzResult.Success -> {
                _uiState.update { state ->
                    state.copy(agentsList = result.data.getAgentsNewToOld())
                }
            }

            is ZzzResult.Error -> {
                println("get agents error: ${result.exception}")
            }
        }
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
        when (val result = bangbooRepository.getBangbooList()) {
            is ZzzResult.Success -> {
                _uiState.update { state ->
                    state.copy(bangbooList = result.data.getBangbooNewToOld())
                }
            }

            is ZzzResult.Error -> {
                println("get bangboo error: ${result.exception}")
            }
        }
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