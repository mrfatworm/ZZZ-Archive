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
        assertTrue(isDark)
    }

    @Test
    fun `Set Dark Theme`() {
        viewModel.setIsDarkTheme(false)
        val state = viewModel.isDark.value
        assertFalse(state)
    }
}