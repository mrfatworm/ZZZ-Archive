/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.setting.domain


import MainDispatcherRule
import org.junit.Rule
import setting.FakeSettingRepository
import setting.SettingsRepository
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SettingViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var viewModel: SettingViewModel

    @BeforeTest
    fun setup() {
        settingsRepository = FakeSettingRepository()
        viewModel = SettingViewModel(settingsRepository)
    }

    @Test
    fun `Init Data Success`() {
        val isDark = viewModel.isDark.value
        val state = viewModel.uiState.value
        assertTrue(isDark)
        assertEquals("en", state.language.project)
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
        assertEquals("zh", state.language.project)
    }
}