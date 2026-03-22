/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.feedback.presentation

import MainDispatcherRule
import feature.feedback.data.GoogleDocRepository
import feature.feedback.model.feedbackIssueTypes
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.LanguageUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import utils.Language

class FeedbackViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val appInfoUseCase = mockk<AppInfoUseCase>()
    private val googleDocRepository = mockk<GoogleDocRepository>()
    private val languageUseCase = mockk<LanguageUseCase>()
    private lateinit var viewModel: FeedbackViewModel

    @BeforeTest
    fun setup() {
        every { appInfoUseCase.getAppVersion() } returns "Luciana 2024.11.13"
        every { appInfoUseCase.getDeviceInfo() } returns "Pixel 9 Pro"
        every { appInfoUseCase.getDeviceOs() } returns "Android 35"
        coEvery { languageUseCase.getLanguage() } returns flowOf(Language.English)
        coEvery {
            googleDocRepository.submitFeedbackForm(
                issueType = any(),
                language = any(),
                issueDesc = any(),
                email = any(),
                appVersion = any(),
                deviceName = any(),
                operatingSystem = any()
            )
        } returns Result.success(Unit)

        viewModel =
            FeedbackViewModel(
                appInfoUseCase = appInfoUseCase,
                googleDocRepository = googleDocRepository,
                languageUseCase = languageUseCase
            )
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals("en", state.language)
        assertEquals("Luciana 2024.11.13", state.appVersion)
        assertEquals("Pixel 9 Pro", state.deviceName)
        assertEquals("Android 35", state.operatingSystem)
    }

    @Test
    fun `Submit Feedback Success`() {
        viewModel.onAction(
            FeedbackAction.OnSelectedIssueChange(
                feedbackIssueTypes[2]
            )
        )
        viewModel.onAction(
            FeedbackAction.OnDescTextFieldChange(
                "Issue Desc"
            )
        )
        viewModel.onAction(FeedbackAction.SubmitForm)
        val state = viewModel.uiState.value
        assertTrue(state.showSubmitSuccessDialog)
    }

    @Test
    fun `Submit Feedback With Unspecified Issue Type`() {
        viewModel.onAction(
            FeedbackAction.OnDescTextFieldChange(
                "Issue Desc"
            )
        )
        viewModel.onAction(FeedbackAction.SubmitForm)
        val state = viewModel.uiState.value
        assertTrue(state.invalidForm)
    }

    @Test
    fun `Submit Feedback With Empty Issue Content`() {
        viewModel.onAction(
            FeedbackAction.OnSelectedIssueChange(
                feedbackIssueTypes[2]
            )
        )
        viewModel.onAction(FeedbackAction.SubmitForm)
        val state = viewModel.uiState.value
        assertTrue(state.invalidForm)
    }
}
