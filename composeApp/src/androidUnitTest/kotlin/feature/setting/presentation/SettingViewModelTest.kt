/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.setting.presentation


import MainDispatcherRule
import android.content.Context
import feature.setting.data.FakeAppInfoRepository
import feature.setting.data.FakeSettingRepository
import feature.setting.domain.FakeLanguageUseCase
import feature.setting.domain.SettingViewModel
import io.mockk.mockk
import org.junit.Rule
import utils.AppActions
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SettingViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockContext = mockk<Context>(relaxed = true)
    private val settingsRepository = FakeSettingRepository()
    private val appInfoRepository = FakeAppInfoRepository()
    private val appActions = AppActions(mockContext)
    private val languageUseCase = FakeLanguageUseCase()
    private lateinit var viewModel: SettingViewModel

    @BeforeTest
    fun setup() {
        viewModel =
            SettingViewModel(settingsRepository, appInfoRepository, appActions, languageUseCase)
    }

    @Test
    fun `Init Data Success`() {
        val isDark = viewModel.isDark.value
        val state = viewModel.uiState.value
        assertTrue(isDark)
        assertEquals("en", state.language.code)
    }

    @Test
    fun `Set Dark Theme`() {
        viewModel.setIsDarkTheme(false)
        val state = viewModel.isDark.value
        assertFalse(state)
    }

    @Test
    fun `Set Language`() {
        viewModel.setLanguage("zh")
        val state = viewModel.uiState.value
        assertEquals("zh", state.language.code)
    }

}