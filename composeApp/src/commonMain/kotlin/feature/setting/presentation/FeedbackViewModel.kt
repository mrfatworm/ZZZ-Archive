/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.GoogleDocUseCase
import feature.setting.domain.LanguageUseCase
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
    private val appInfoUseCase: AppInfoUseCase,
    private val googleDocUseCase: GoogleDocUseCase,
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
                    invalidForm = true, invalidMessage = Res.string.invalid_form
                )
            }
        } else {
            _uiState.update { it.copy(invalidForm = false) }
            viewModelScope.launch {
                postGoogleDoc(issueTypeIndex, issueContent, nickname)
            }
        }
    }

    private suspend fun postGoogleDoc(
        issueTypeIndex: FeedbackIssueType, issueContent: String, nickname: String
    ) {
        val result = googleDocUseCase.submitFeedbackForm(
            issueTypeIndex.chtString,
            uiState.value.language,
            issueContent,
            nickname,
            uiState.value.appVersion,
            uiState.value.deviceName,
            uiState.value.operatingSystem
        )
        result.fold(onSuccess = {
            _uiState.update { it.copy(showSubmitSuccessDialog = true) }
        }, onFailure = {
            _uiState.update {
                it.copy(
                    invalidForm = true, invalidMessage = Res.string.unknown_error
                )
            }
        })
    }

    fun dismissSubmitSuccessDialog() {
        _uiState.update { it.copy(showSubmitSuccessDialog = false) }
    }

    private fun getLanguage() {
        val language = languageUseCase.getLanguage()
        _uiState.update { it.copy(language = language.code) }
    }

    private fun getAppVersion() {
        val appVersion = appInfoUseCase.getAppVersion()
        _uiState.update { it.copy(appVersion = appVersion) }
    }

    private fun getDeviceInfo() {
        val deviceName = appInfoUseCase.getDeviceInfo()
        _uiState.update { it.copy(deviceName = deviceName) }
    }

    private fun getDeviceOs() {
        val deviceOs = appInfoUseCase.getDeviceOs()
        _uiState.update { it.copy(operatingSystem = deviceOs) }
    }
}