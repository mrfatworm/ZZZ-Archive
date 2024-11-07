/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.setting.domain


import MainDispatcherRule
import app.setting.data.FakeAppInfoRepository
import app.setting.data.FakeSettingRepository
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FeedbackViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val settingsRepository = FakeSettingRepository()
    private val appInfoRepository = FakeAppInfoRepository()
    private lateinit var viewModel: FeedbackViewModel

    @BeforeTest
    fun setup() {
        viewModel = FeedbackViewModel(settingsRepository, appInfoRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.appVersion, "Lucy 2024.11")
        assertEquals(state.deviceName, "Pixel 9 Pro")
        assertEquals(state.operatingSystem, "Android 35")

    }

}