/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.feedback.presentation

import MainDispatcherTest
import feature.feedback.data.FakeGoogleDocRepository
import feature.feedback.model.feedbackIssueTypes
import feature.setting.domain.FakeAppInfoUseCase
import feature.setting.domain.FakeLanguageUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FeedbackViewModelTest : MainDispatcherTest() {
    private val appInfoUseCase = FakeAppInfoUseCase()
    private val googleDocRepository = FakeGoogleDocRepository()
    private val viewModel =
        FeedbackViewModel(
            appInfoUseCase = appInfoUseCase,
            googleDocRepository = googleDocRepository,
            languageUseCase = FakeLanguageUseCase()
        )

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals("en", state.language)
        assertEquals(appInfoUseCase.getAppVersion(), state.appVersion)
        assertEquals(appInfoUseCase.getDeviceInfo(), state.deviceName)
        assertEquals(appInfoUseCase.getDeviceOs(), state.operatingSystem)
    }

    @Test
    fun `Submit Feedback Success`() {
        viewModel.onAction(FeedbackAction.OnSelectedIssueChange(feedbackIssueTypes[2]))
        viewModel.onAction(FeedbackAction.OnDescTextFieldChange("Issue Desc"))
        viewModel.onAction(FeedbackAction.SubmitForm)
        val state = viewModel.uiState.value
        assertTrue(state.showSubmitSuccessDialog)
    }

    @Test
    fun `Submit Feedback Fail`() {
        googleDocRepository.setError(true)
        viewModel.onAction(FeedbackAction.OnSelectedIssueChange(feedbackIssueTypes[2]))
        viewModel.onAction(FeedbackAction.OnDescTextFieldChange("Issue Desc"))
        viewModel.onAction(FeedbackAction.SubmitForm)
        val state = viewModel.uiState.value
        assertTrue(state.invalidForm)
    }

    @Test
    fun `Submit Feedback With Unspecified Issue Type`() {
        viewModel.onAction(FeedbackAction.OnDescTextFieldChange("Issue Desc"))
        viewModel.onAction(FeedbackAction.SubmitForm)
        val state = viewModel.uiState.value
        assertTrue(state.invalidForm)
    }

    @Test
    fun `Submit Feedback With Empty Issue Content`() {
        viewModel.onAction(FeedbackAction.OnSelectedIssueChange(feedbackIssueTypes[2]))
        viewModel.onAction(FeedbackAction.SubmitForm)
        val state = viewModel.uiState.value
        assertTrue(state.invalidForm)
    }
}
