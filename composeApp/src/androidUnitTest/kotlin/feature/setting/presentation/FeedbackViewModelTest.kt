/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation


import MainDispatcherRule
import feature.setting.domain.AppInfoUseCase
import feature.setting.domain.GoogleDocUseCase
import feature.setting.domain.LanguageUseCase
import feature.setting.model.feedbackIssueTypes
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FeedbackViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val appInfoUseCase = mockk<AppInfoUseCase>()
    private val googleDocUseCase = mockk<GoogleDocUseCase>()
    private val languageUseCase = mockk<LanguageUseCase>()
    private lateinit var viewModel: FeedbackViewModel

    @BeforeTest
    fun setup() {
        every { appInfoUseCase.getAppVersion() } returns "Luciana 2024.11.13"
        every { appInfoUseCase.getDeviceInfo() } returns "Pixel 9 Pro"
        every { appInfoUseCase.getDeviceOs() } returns "Android 35"
        every { languageUseCase.getLanguage().code } returns "en"
        coEvery {
            googleDocUseCase.submitFeedbackForm(
                any(), any(), any(), any(), any(), any(), any()
            )
        } returns Result.success(Unit)

        viewModel = FeedbackViewModel(appInfoUseCase, googleDocUseCase, languageUseCase)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.language, "en")
        assertEquals(state.appVersion, "Luciana 2024.11.13")
        assertEquals(state.deviceName, "Pixel 9 Pro")
        assertEquals(state.operatingSystem, "Android 35")
    }

    @Test
    fun `Submit Feedback Success`() {
        viewModel.onAction(
            FeedbackAction.SubmitForm(
                feedbackIssueTypes[2],
                "Issue Desc",
                "Mr.fatworm"
            )
        )
        val state = viewModel.uiState.value
        println(state.showSubmitSuccessDialog)
        assertTrue(state.showSubmitSuccessDialog)
    }

    @Test
    fun `Submit Feedback With Unspecified Issue Type`() {
        viewModel.onAction(
            FeedbackAction.SubmitForm(
                feedbackIssueTypes.first(),
                "Issue Desc",
                "Mr.fatworm"
            )
        )
        val state = viewModel.uiState.value
        assertTrue(state.invalidForm)
    }

    @Test
    fun `Submit Feedback With Empty Issue Content`() {
        viewModel.onAction(FeedbackAction.SubmitForm(feedbackIssueTypes[2], "", "Mr.fatworm"))
        val state = viewModel.uiState.value
        assertTrue(state.invalidForm)
    }

}