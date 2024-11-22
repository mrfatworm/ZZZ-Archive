/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.setting.data.AppInfoRepository
import feature.setting.data.GoogleDocRepository
import feature.setting.model.FeedbackIssueType
import feature.setting.model.FeedbackState
import feature.setting.model.feedbackIssueTypes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.invalid_form
import zzzarchive.composeapp.generated.resources.unknown_error

class FeedbackViewModel(
    private val appInfoRepository: AppInfoRepository,
    private val googleDocRepository: GoogleDocRepository,
    private val languageUseCase: LanguageUseCase
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

    private fun getLanguage() {
        val language = languageUseCase.getLanguage()
        _uiState.update { it.copy(language = language.code) }
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