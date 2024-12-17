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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
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

    private var gameRecordJob: Job? = null
    private var officialNewsJob: Job? = null
    private var defaultAccountJob: Job? = null
    private var coverImageJob: Job? = null
    private var agentsListJob: Job? = null
    private var wEnginesListJob: Job? = null
    private var bangbooListJob: Job? = null
    private var drivesListJob: Job? = null
    private var pixivTopicJob: Job? = null

    private var _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.onStart {
        // If you leave here more than 8s ,back to home screen will trigger it
        updateDatabaseUseCase.updateAssetsIfNewVersionAvailable()
        updatePixivTopic()
        updateBanner()
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(8000L), _uiState.value
    )

    init {
        updateOfficialNewsEveryTenMinutes()
        observeDefaultAccount()
        observeCoverImage()
        observeAgentsList()
        observeWEnginesList()
        observeBangbooList()
        observeDrivesList()
        observePixivTopic()
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
                    updatePixivTopic(action.tag)
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

    private suspend fun closeBannerAndIgnoreId(id: Int) {
        bannerUseCase.setBannerIgnoreId(id)
        _uiState.update { state ->
            state.copy(banner = null)
        }
    }

    private suspend fun updateBanner() {
        bannerUseCase.invoke().fold(onSuccess = { banner ->
            _uiState.update {
                it.copy(banner = banner)
            }
        }, onFailure = {
            println("get banner result: ${it.message}")
        })
    }

    private fun observeCoverImage() {
        coverImageJob?.cancel()
        coverImageJob = viewModelScope.launch {
            coverImageUseCase.invoke().collect { coverImagesList ->
                _uiState.update {
                    it.copy(coverImage = coverImagesList)
                }
            }
        }
    }


    private fun observeDefaultAccount() {
        defaultAccountJob?.cancel()
        defaultAccountJob = gameRecordUseCase.getDefaultUid().onEach { defaultAccountUid ->
            val defaultAccount =
                gameRecordUseCase.getDefaultHoYoLabAccount(defaultAccountUid).firstOrNull()
                    ?: return@onEach
            _uiState.update { state ->
                state.copy(
                    gameRecord = emptyGameRecordState.copy(
                        hasAccount = true,
                        nickname = defaultAccount.nickName,
                        server = defaultAccount.regionName,
                        uid = defaultAccount.uid.toString(),
                        profileUrl = defaultAccount.profileUrl,
                        cardUrl = defaultAccount.cardUrl
                    )
                )
            }
            updateGameRecordEveryTenMinutes()
        }.launchIn(viewModelScope)
    }


    private fun updateOfficialNewsEveryTenMinutes() {
        officialNewsJob?.cancel()
        officialNewsJob = newsUseCase.getNewsPeriodically(10, 6).onEach { result ->
            result.fold(onSuccess = { newsList ->
                _uiState.update { state ->
                    state.copy(newsList = newsUseCase.convertToOfficialNewsState(newsList))
                }
            }, onFailure = {
                println("get news error: ${it.message}")
            })
        }.launchIn(viewModelScope)
    }

    private fun updateGameRecordEveryTenMinutes() {
        gameRecordJob?.cancel()
        gameRecordJob = gameRecordUseCase.getGameRecordPeriodically(10).onEach { result ->
            result.fold(onSuccess = { gameRecord ->
                _uiState.update { state ->
                    state.copy(
                        gameRecord = gameRecord.toGameRecordState(
                            state.gameRecord.hasAccount,
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
        }.launchIn(viewModelScope)
    }

    private fun observePixivTopic() {
        pixivTopicJob?.cancel()
        pixivTopicJob = pixivUseCase.invoke().onEach { pixivArticleList ->
            _uiState.update {
                it.copy(pixivTopics = pixivArticleList)
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun updatePixivTopic(zzzTag: String = pixivTagDropdownItems.first().tagOnPixiv) =
        pixivUseCase.updateZzzTopic(zzzTag)

    private suspend fun sign() {
        if (uiState.value.signResult != null) return
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
        delay(5000) // Cooldown
        _uiState.update { state ->
            state.copy(signResult = null)
        }
    }

    private fun observeAgentsList() {
        agentsListJob?.cancel()
        agentsListJob = viewModelScope.launch {
            agentsListUseCase.invoke().collect { agentsList ->
                _uiState.update {
                    it.copy(agentsList = agentsList)
                }
            }
        }
    }

    private fun observeWEnginesList() {
        wEnginesListJob?.cancel()
        wEnginesListJob = viewModelScope.launch {
            wEnginesListUseCase.invoke().collect { wEnginesList ->
                _uiState.update {
                    it.copy(wEnginesList = wEnginesList)
                }
            }
        }
    }


    private fun observeBangbooList() {
        bangbooListJob?.cancel()
        bangbooListJob = viewModelScope.launch {
            bangbooListUseCase.invoke().collect { bangbooList ->
                _uiState.update {
                    it.copy(bangbooList = bangbooList)
                }
            }
        }
    }

    private fun observeDrivesList() {
        drivesListJob?.cancel()
        drivesListJob = viewModelScope.launch {
            drivesListUseCase.invoke().collect { drivesList ->
                _uiState.update {
                    it.copy(drivesList = drivesList)
                }
            }
        }
    }
}