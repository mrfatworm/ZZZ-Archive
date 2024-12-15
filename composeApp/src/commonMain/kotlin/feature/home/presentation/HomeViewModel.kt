/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import database.UpdateDatabaseUseCase
import feature.agent.domain.AgentsListUseCase
import feature.bangboo.domain.BangbooListUseCase
import feature.banner.domain.BannerUseCase
import feature.cover_image.domain.CoverImageUseCase
import feature.drive.domain.DrivesListUseCase
import feature.home.model.pixivTagDropdownItems
import feature.hoyolab.data.mapper.toGameRecordState
import feature.hoyolab.domain.GameRecordUseCase
import feature.news.domain.OfficialNewsUseCase
import feature.pixiv.domain.PixivUseCase
import feature.wengine.domain.WEnginesListUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
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
    private val updateDatabaseUseCase: UpdateDatabaseUseCase,
    private val gameRecordUseCase: GameRecordUseCase
) : ViewModel() {

    private var ignoreBannerId = 0
    private var _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            launch { ignoreBannerId = bannerUseCase.getBannerIgnoreId().first() }
            launch { updateDatabaseUseCase.updateAssetsIfNewVersionAvailable() }
            launch { fetchBanner() }
            launch { observeCoverImage() }
            launch { observeDefaultAccount() }
            launch { fetchZzzOfficialNewsEveryTenMinutes() }
            launch { fetchPixivTopic() }
            launch { observeAgentsList() }
            launch { observeWEnginesList() }
            launch { observeBangbooList() }
            launch { observeDrivesList() }
        }
    }

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.DismissBanner -> {
                viewModelScope.launch {
                    closeBannerAndIgnoreId(action.id)
                }
            }

            is HomeAction.ChangePixivTag -> {
                viewModelScope.launch {
                    fetchPixivTopic(action.tag)
                }
            }

            is HomeAction.Sign -> {
                viewModelScope.launch {
                    sign()
                }
            }

            else -> {}
        }
    }

    suspend fun closeBannerAndIgnoreId(id: Int) {
        ignoreBannerId = id
        bannerUseCase.setBannerIgnoreId(id)
        _uiState.update { state ->
            state.copy(banner = null)
        }
    }

    private suspend fun fetchBanner() {
        val result = bannerUseCase.invoke()
        result.fold(onSuccess = { banner ->
            if (banner.id > ignoreBannerId) {
                _uiState.update {
                    it.copy(banner = banner)
                }
            }
        }, onFailure = {
            println("get banner error: ${it.message}")
        })
    }

    private suspend fun observeCoverImage() {
        coverImageUseCase.invoke().collect { coverImagesList ->
            _uiState.update {
                it.copy(coverImage = coverImagesList)
            }
        }
    }

    private suspend fun observeDefaultAccount() {
        gameRecordUseCase.getDefaultUid().collect {
            val defaultAccount = gameRecordUseCase.getDefaultHoYoLabAccount(it).firstOrNull()
                ?: return@collect
            _uiState.update { state ->
                state.copy(
                    gameRecord = emptyGameRecordState.copy(
                        nickname = defaultAccount.nickName,
                        server = defaultAccount.regionName,
                        uid = defaultAccount.uid.toString(),
                        profileUrl = defaultAccount.profileUrl,
                        cardUrl = defaultAccount.cardUrl
                    )
                )
            }
            fetchGameRecordEveryTenMinutes()
        }
    }

    private suspend fun fetchZzzOfficialNewsEveryTenMinutes() {
        newsUseCase.getNewsPeriodically(10, 6).collect { result ->
            result.fold(onSuccess = { newsList ->
                _uiState.update { state ->
                    state.copy(newsList = newsUseCase.convertToOfficialNewsState(newsList))
                }
            }, onFailure = {
                println("get news error: ${it.message}")
            })
        }
    }

    private fun fetchGameRecordEveryTenMinutes() {
        viewModelScope.launch {
            gameRecordUseCase.getGameRecordPeriodically(10).collect { result ->
                result.fold(onSuccess = { gameRecord ->
                    _uiState.update { state ->
                        state.copy(
                            gameRecord = gameRecord.toGameRecordState(
                                state.gameRecord.nickname,
                                state.gameRecord.server,
                                state.gameRecord.uid,
                                state.gameRecord.profileUrl,
                                state.gameRecord.cardUrl
                            )
                        )
                    }
                }, onFailure = {
                    println("get game record error: ${it.message}")
                })
            }
        }
    }

    private suspend fun fetchPixivTopic(zzzTag: String = pixivTagDropdownItems.first().tagOnPixiv) {
        val result = pixivUseCase.invoke(zzzTag)
        result.fold(onSuccess = { pixivTopic ->
            _uiState.update {
                it.copy(pixivTopics = pixivTopic)
            }
        }, onFailure = {
            println("get pixiv topic error: ${it.message}")
        })
    }

    private suspend fun sign() {
        _uiState.update { state ->
            state.copy(signResult = " =U= ")
        }
        gameRecordUseCase.sign().fold(onSuccess = {
            _uiState.update { state ->
                state.copy(signResult = it.message)
            }
        }, onFailure = {
            _uiState.update { state ->
                state.copy(signResult = it.message)
            }
        })
        delay(5000)
        _uiState.update { state ->
            state.copy(signResult = null)
        }
    }

    private suspend fun observeAgentsList() {
        agentsListUseCase.invoke().collect { agentsList ->
            _uiState.update {
                it.copy(agentsList = agentsList)
            }
        }
    }

    private suspend fun observeWEnginesList() {
        wEnginesListUseCase.invoke().collect { wEnginesList ->
            _uiState.update {
                it.copy(wEnginesList = wEnginesList)
            }
        }
    }

    private suspend fun observeBangbooList() {
        bangbooListUseCase.invoke().collect { bangbooList ->
            _uiState.update {
                it.copy(bangbooList = bangbooList)
            }
        }
    }

    private suspend fun observeDrivesList() {
        drivesListUseCase.invoke().collect { drivesList ->
            _uiState.update {
                it.copy(drivesList = drivesList)
            }
        }
    }
}