/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.agent.data.AgentRepository
import app.home.data.BannerRepository
import app.home.data.NewsRepository
import app.home.data.PixivRepository
import app.home.model.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.ZzzResult

class HomeViewModel(
    private val bannerRepository: BannerRepository,
    private val pixivRepository: PixivRepository,
    private val newsRepository: NewsRepository,
    private val agentRepository: AgentRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
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
        viewModelScope.launch {
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
        viewModelScope.launch {
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
        viewModelScope.launch {
            when (val result = agentRepository.getAgentsList()) {
                is ZzzResult.Success -> {
                    _uiState.update { state ->
                        state.copy(agentsList = result.data.agents)
                    }
                }

                is ZzzResult.Error -> {
                    println("get agents error: ${result.exception}")
                }
            }
        }
    }
}