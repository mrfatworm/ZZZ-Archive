/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.domain

import androidx.lifecycle.ViewModel
import app.setting.data.AppInfoRepository
import app.setting.data.SettingsRepository
import app.setting.model.FeedbackState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FeedbackViewModel(
    private val settingsRepository: SettingsRepository,
    private val appInfoRepository: AppInfoRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(FeedbackState())
    val uiState = _uiState.asStateFlow()
    private var language: String = "unknown"

    init {
        getLanguage()
        getAppVersion()
        getDeviceInfo()
        getDeviceOs()
    }

    private fun getLanguage() {
        val language = settingsRepository.getLanguage()
        this.language = language
    }

    private fun getAppVersion() {
        val appVersion = appInfoRepository.getAppVersion()
        _uiState.update { it.copy(appVersion = appVersion) }
    }

    private fun getDeviceInfo() {
        val deviceName = appInfoRepository.getDeviceInfo()
        _uiState.update { it.copy(deviceName = deviceName) }
    }

    private fun getDeviceOs() {
        val deviceOs = appInfoRepository.getDeviceOs()
        _uiState.update { it.copy(operatingSystem = deviceOs) }
    }
}