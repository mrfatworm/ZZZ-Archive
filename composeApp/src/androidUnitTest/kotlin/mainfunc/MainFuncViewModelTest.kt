/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package mainfunc


import MainDispatcherRule
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Rule
import setting.FakeSettingRepository
import setting.SettingsRepository
import kotlin.test.BeforeTest
import kotlin.test.Test

class MainFuncViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var settingsRepository: SettingsRepository
    private lateinit var viewModel: MainFuncViewModel

    @BeforeTest
    fun setup() {
        settingsRepository = FakeSettingRepository()
        viewModel = MainFuncViewModel(settingsRepository)
    }

    @Test
    fun `Init Data Success`() {
        val isDark = viewModel.isDark.value
        assertThat(isDark).isEqualTo(true)
    }

    @Test
    fun `Set Dark Theme`() {
        viewModel.setIsDarkTheme(false)
        val state = viewModel.isDark.value
        assertThat(state).isEqualTo(false)
    }
}