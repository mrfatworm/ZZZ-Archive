/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.domain


import MainDispatcherRule
import feature.setting.data.FakeAppInfoRepository
import feature.setting.data.FakeGoogleDocRepository
import feature.setting.data.FakeSettingRepository
import feature.setting.model.feedbackIssueTypes
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FeedbackViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val settingsRepository = FakeSettingRepository()
    private val appInfoRepository = FakeAppInfoRepository()
    private val googleDocRepository = FakeGoogleDocRepository()
    private lateinit var viewModel: FeedbackViewModel

    @BeforeTest
    fun setup() {
        viewModel = FeedbackViewModel(settingsRepository, appInfoRepository, googleDocRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.language, "en")
        assertEquals(state.appVersion, "Lucy 2024.11")
        assertEquals(state.deviceName, "Pixel 9 Pro")
        assertEquals(state.operatingSystem, "Android 35")
    }

    @Test
    fun `Submit Feedback Success`() {
        viewModel.submitFeedback(feedbackIssueTypes[2], "Issue Desc", "Mr.fatworm")
        val state = viewModel.uiState.value
        println(state.showSubmitSuccessDialog)
        assertTrue(state.showSubmitSuccessDialog)
    }

    @Test
    fun `Submit Feedback With Unspecified Issue Type`() {
        viewModel.submitFeedback(feedbackIssueTypes.first(), "Issue Desc", "Mr.fatworm")
        val state = viewModel.uiState.value
        assertTrue(state.invalidForm)
    }

    @Test
    fun `Submit Feedback With Empty Issue Content`() {
        viewModel.submitFeedback(feedbackIssueTypes[2], "", "Mr.fatworm")
        val state = viewModel.uiState.value
        assertTrue(state.invalidForm)
    }

}