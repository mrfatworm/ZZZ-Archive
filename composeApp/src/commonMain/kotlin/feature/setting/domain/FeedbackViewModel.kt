/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.domain

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.setting.data.AppInfoRepository
import feature.setting.data.GoogleDocRepository
import feature.setting.data.SettingsRepository
import feature.setting.model.FeedbackIssueType
import feature.setting.model.FeedbackState
import feature.setting.model.feedbackIssueTypes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import utils.Language
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.invalid_form
import zzzarchive.composeapp.generated.resources.unknown_error

class FeedbackViewModel(
    private val settingsRepository: SettingsRepository,
    private val appInfoRepository: AppInfoRepository,
    private val googleDocRepository: GoogleDocRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(FeedbackState())
    val uiState = _uiState.asStateFlow()

    init {
        getLanguage()
        getAppVersion()
        getDeviceInfo()
        getDeviceOs()
    }

    fun submitFeedback(issueTypeIndex: FeedbackIssueType, issueContent: String, nickname: String) {
        if (issueTypeIndex == feedbackIssueTypes.first() || issueContent.isBlank()) {
            _uiState.update {
                it.copy(
                    invalidForm = true,
                    invalidMessage = Res.string.invalid_form
                )
            }
        } else {
            _uiState.update { it.copy(invalidForm = false) }
            viewModelScope.launch {
                val result = googleDocRepository.submitFeedbackForm(
                    issueTypeIndex.chtString,
                    uiState.value.language,
                    issueContent,
                    nickname,
                    uiState.value.appVersion,
                    uiState.value.deviceName,
                    uiState.value.operatingSystem
                )
                if (result) {
                    _uiState.update { it.copy(showSubmitSuccessDialog = true) }
                } else {
                    _uiState.update {
                        it.copy(
                            invalidForm = true,
                            invalidMessage = Res.string.unknown_error
                        )
                    }
                }
            }
        }
    }

    fun dismissSubmitSuccessDialog() {
        _uiState.update { it.copy(showSubmitSuccessDialog = false) }
    }

    private fun getLanguage(defaultLanguage: String = Locale.current.language) {
        val langCode = settingsRepository.getLanguage()
        val language =
            if (langCode == "") Language.entries.firstOrNull { it.project == defaultLanguage }
                ?: Language.English
            else Language.entries.firstOrNull { it.project == langCode } ?: Language.English
        _uiState.update { it.copy(language = language.project) }
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