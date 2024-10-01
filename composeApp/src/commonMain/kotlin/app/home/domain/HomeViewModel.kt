/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.agent.data.AgentRepository
import app.bangboo.data.BangbooRepository
import app.drive.data.DriveRepository
import app.home.data.BannerRepository
import app.home.data.NewsRepository
import app.home.data.PixivRepository
import app.home.model.HomeState
import app.wengine.data.WEngineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.ZzzResult

class HomeViewModel(
    private val bannerRepository: BannerRepository,
    private val pixivRepository: PixivRepository,
    private val newsRepository: NewsRepository,
    private val agentRepository: AgentRepository,
    private val wEngineRepository: WEngineRepository,
    private val bangbooRepository: BangbooRepository,
    private val driveRepository: DriveRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            launch { fetchBannerImage() }
            launch { fetchZzzOfficialNewsEveryTenMinutes() }
            launch { fetchPixivTopicEveryTenMinutes() }
            launch { fetchAgentsList() }
            launch { fetchWEnginesList() }
            launch { fetchBangbooList() }
            launch { fetchDrivesList() }
        }
    }

    private suspend fun fetchBannerImage() {
        when (val result = bannerRepository.getBanner()) {
            is ZzzResult.Success -> {
                _uiState.update { state ->
                    state.copy(banner = result.data)
                }
            }

            is ZzzResult.Error -> {
                println("get banner error: ${result.exception}")
            }
        }
    }

    private suspend fun fetchZzzOfficialNewsEveryTenMinutes() {
        newsRepository.getNewsPeriodically(10, 5, "en-us").collect { result ->
            when (result) {
                is ZzzResult.Success -> {
                    _uiState.update { state ->
                        state.copy(news = result.data)
                    }
                }

                is ZzzResult.Error -> {
                    println("get news error: ${result.exception}")
                }
            }
        }
    }

    private suspend fun fetchPixivTopicEveryTenMinutes() {
        pixivRepository.getZzzTopicPeriodically(10).collect { result ->
            when (result) {
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